package jskj.com.naprioridetectclient.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jskj.com.naprioridetectclient.R;
import jskj.com.naprioridetectclient.contant.DBConstant;
import jskj.com.naprioridetectclient.contant.MsgContent;
import jskj.com.naprioridetectclient.entry.AppInfo;
import jskj.com.naprioridetectclient.ui.NetWorkUtils;
import jskj.com.naprioridetectclient.util.ApkFileUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class RemoteFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "RemoteFragment";
    private Button mBtnNetIsUse;
    private Button mBtnSelectApk;
    private TextView mTvDetectResult;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_remote, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mBtnNetIsUse = (Button) view.findViewById(R.id.btn_test_network);
        mBtnSelectApk = (Button) view.findViewById(R.id.btn_detect_apk);
        mTvDetectResult = (TextView) view.findViewById(R.id.tv_detect_result);
        initEvent();
    }

    private void initEvent() {
        mBtnSelectApk.setOnClickListener(this);
        mBtnNetIsUse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test_network:
                boolean result = NetWorkUtils.isNetWorkAvailable(getActivity());
                if (result) {
                    Toast.makeText(getActivity(), "网络可用，可以进行在线检测!!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "网络不可用，请检查网络连接!!!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_detect_apk:
                onlineDetectApk();
                break;
        }
    }

    private void onlineDetectApk() {
        Intent intent = new Intent();
        intent.setType("application/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, MsgContent.REMOTE_MSG_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data == null) {
            Log.e(TAG, "don't select any apk file");
            return;
        }
        String apkFilePath = data.getData().getPath();
        File apkFile = new File(apkFilePath);

        if (!apkFile.exists()) {
            Toast.makeText(getActivity(), "该apk文件已经不存在了，请重新选择!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        AppInfo apkInfo = ApkFileUtils.getApkInfo(getActivity(), apkFilePath);
        uploadApkFile(apkFile, apkInfo);
    }

    public void uploadApkFile(File file, AppInfo apkInfo) {
        Map<String, String> params = new HashMap<>();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart(DBConstant.NAME, apkInfo.name);
        builder.addFormDataPart(DBConstant.VERSION_NAME, apkInfo.versionName);
        builder.addFormDataPart(DBConstant.VERSION_CODE, apkInfo.versionCode + "");

//        builder.addFormDataPart("apk", file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file));
        MultipartBody multipartBody = builder.build();
        okhttp3.Request request = new okhttp3.Request.Builder().url("")
                .post(multipartBody).build();
        OkHttpClient client = new OkHttpClient.Builder().build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse: success");
            }
        });
    }
}
