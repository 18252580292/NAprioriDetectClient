package jskj.com.naprioridetectclient.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import jskj.com.naprioridetectclient.R;
import jskj.com.naprioridetectclient.contant.MsgContent;
import jskj.com.naprioridetectclient.ui.NetWorkUtils;

public class RemoteFragment extends Fragment implements View.OnClickListener {
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
        startActivityForResult(intent, MsgContent.remote_msg_request_code);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String apkFilePath = data.getData().getPath();
        File apkFile = new File(apkFilePath);
        if (!apkFile.exists()) {
            Toast.makeText(getActivity(), "该apk文件已经不存在了，请重新选择!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        uploadApkFile(apkFile);
    }

    public void uploadApkFile(File file) {

    }
}
