package jskj.com.naprioridetectclient.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

import jskj.com.naprioridetectclient.R;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private TextView mTxtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        initView();
        initData();
    }

    private void initData() {

    }

    private void initView() {
        mTxtTitle = (TextView) findViewById(R.id.txt_title_home);
    }

}
