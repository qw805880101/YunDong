package com.example.tc.yundong.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tc.yundong.R;
import com.zhy.autolayout.AutoLinearLayout;

/**
 * 登录界面
 * Created by tc on 2016/7/1.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    private EditText et_account = null, et_password = null;

    private Button bt_login = null;
    private TextView tx_wangji = null;

    private AutoLinearLayout lin_wb_login = null, lin_qq_login = null, lin_wx_login = null;

    private String account = "";
    private String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        et_account = (EditText) findViewById(R.id.et_account);
        et_password = (EditText) findViewById(R.id.et_password);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);
        tx_wangji = (TextView) findViewById(R.id.tx_wangji);
        tx_wangji.setOnClickListener(this);
        lin_wb_login = (AutoLinearLayout) findViewById(R.id.lin_wb_longin);
        lin_wb_login.setOnClickListener(this);
        lin_qq_login = (AutoLinearLayout) findViewById(R.id.lin_qq_longin);
        lin_qq_login.setOnClickListener(this);
        lin_wx_login = (AutoLinearLayout) findViewById(R.id.lin_wx_longin);
        lin_wx_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == bt_login){
            login();
        } else if (v == tx_wangji){

        } else if (v == lin_wb_login){

        } else if (v == lin_qq_login){

        } else if (v == lin_wx_login){

        }
    }

    /**
     * 登录
     */
    private void login() {
        account = et_account.getText().toString().trim();
        password = et_password.getText().toString().trim();
        if (account == null && account.equals("")){
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        } else if (password == null && password.equals("")){
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, RegisterActivity.class);
        this.startActivity(intent);
    }
}
