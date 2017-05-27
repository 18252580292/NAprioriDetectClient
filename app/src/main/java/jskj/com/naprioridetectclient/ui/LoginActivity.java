package jskj.com.naprioridetectclient.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import jskj.com.naprioridetectclient.R;
import jskj.com.naprioridetectclient.util.StringUtils;

public class LoginActivity extends Activity implements View.OnClickListener {
    private Button mLoginBtn;
    private Button mCancelBtn;
    private EditText mUsernameEt;
    private EditText mPwdEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    private void initData() {
        mLoginBtn.setOnClickListener(this);
        mCancelBtn.setOnClickListener(this);
    }

    private void initView() {
        mLoginBtn = (Button) findViewById(R.id.btn_login);
        mCancelBtn = (Button) findViewById(R.id.btn_cancel);
        mUsernameEt = (EditText) findViewById(R.id.et_username);
        mPwdEt = (EditText) findViewById(R.id.et_password);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                processLogin();
                break;
            case R.id.btn_cancel:
                break;
        }
    }

    /**
     * 处理登录请求
     */
    private void processLogin() {
        String username = mUsernameEt.getText().toString();
        String pwd = mPwdEt.getText().toString();
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(pwd)) {
            Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if(username.equals("root") && pwd.equals("root")) {
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, HomeActivity.class));
        }
    }
}
