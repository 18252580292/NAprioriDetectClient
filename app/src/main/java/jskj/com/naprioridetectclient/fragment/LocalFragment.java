package jskj.com.naprioridetectclient.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import jskj.com.naprioridetectclient.R;
import jskj.com.naprioridetectclient.contant.MsgContent;
import jskj.com.naprioridetectclient.db.NAprioriDao;
import jskj.com.naprioridetectclient.entry.AppInfo;
import jskj.com.naprioridetectclient.util.ApkFileUtils;

public class LocalFragment extends Fragment {
    private static final String TAG = "LocalFragment";
    private String detectResult = "";
    private Button mBtnDetectApk;
    private TextView mTvDetectResult;
    private NAprioriDao mDao;
    private ProgressDialog dialog;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            dialog.dismiss();
            AppInfo info = (AppInfo) msg.obj;
            mDao = NAprioriDao.getInstance(getActivity());
            AppInfo result = mDao.queryOne(info.name);
            if (result == null) {
                info.normalApp = -1;
            } else {
                info.normalApp = result.normalApp;
            }
            switch (info.normalApp) {
                case -1:
                    detectResult = "未知,该应用没有录入到本地数据库中，请使用联网查询";
                    break;
                case 0:
                    detectResult = "是恶意应用";
                    break;
                case 1:
                    detectResult = "是正常应用";
                    break;
            }

            mTvDetectResult.setText("应用的名称:" + info.name + "\r\n" + "\r\n"
                    + "应用的包名:" + info.packageName + "\r\n" + "\r\n"
                    + "应用的版本:" + info.versionName + "\r\n" + "\r\n"
                    + "应用的版本号:" + info.versionCode + "\r\n" + "\r\n"
                    + "检测结果:" + detectResult
            );
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_local, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mBtnDetectApk = (Button) view.findViewById(R.id.btn_detect_apk);
        mTvDetectResult = (TextView) view.findViewById(R.id.tv_detect_result);
        initEvent();
    }

    private void initEvent() {
        mBtnDetectApk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("application/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, MsgContent.local_msg_request_code);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode != MsgContent.local_msg_request_code) {
            return;
        }
        if(data == null) {
            return;
        }
        String apkPath = data.getData().getPath();
        AppInfo info = ApkFileUtils.getApkInfo(getActivity(), apkPath);
        Message msg = Message.obtain();
        msg.obj = info;
        msg.what = 0;
        showWaitDialog();
        mHandler.sendMessageDelayed(msg, 3000);
    }

    private void showWaitDialog() {
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("正在检测中，请等待");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();
    }
}
