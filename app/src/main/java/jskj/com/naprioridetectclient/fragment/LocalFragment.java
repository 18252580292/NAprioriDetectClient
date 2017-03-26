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

import jskj.com.naprioridetectclient.R;

public class LocalFragment extends Fragment {
    private Button mBtnDetectApk;
    private TextView mTvDetectResult;

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
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String apkPath = data.getData().getPath();
    }
}
