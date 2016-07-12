package com.example.tc.yundong.Activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tc.yundong.Async.Asyn_Register;
import com.example.tc.yundong.Async.Asyn_SendCode;
import com.example.tc.yundong.R;
import com.example.tc.yundong.Util.Info;
import com.example.tc.yundong.Util.MD5Util;
import com.example.tc.yundong.Util.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册 界面
 * Created by tc on 2016/7/1.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_Phone = null, et_Code = null, et_Password = null, et_Password2 = null;

    private Button bt_getCode = null, bt_Next = null;

    private ImageView im_Eyes = null;

    private String phone = "", code = "", password = "", password2 = "";

    private ImageButton imbt_back = null;

    private TextView tx_title = null;

    private boolean password_Show = false, getCodeType = false;

    private TimeCount time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Info.currentActivity = this;
        Info.currentContext = this;
        setContentView(R.layout.activity_register);
        time = new TimeCount(60000, 1000);
        initView();
    }

    private void initView() {
        imbt_back = (ImageButton) findViewById(R.id.bt_title_back);
        imbt_back.setOnClickListener(this);
        tx_title = (TextView) findViewById(R.id.tx_title);
        tx_title.setText("注册");
        et_Phone = (EditText) findViewById(R.id.et_phone);
        et_Code = (EditText) findViewById(R.id.et_code);
        et_Password = (EditText) findViewById(R.id.et_password_rg);
        et_Password2 = (EditText) findViewById(R.id.et_password2_rg);
        bt_getCode = (Button) findViewById(R.id.bt_getcode);
        bt_getCode.setOnClickListener(this);
        bt_Next = (Button) findViewById(R.id.rg_next);
        bt_Next.setOnClickListener(this);
        im_Eyes = (ImageView) findViewById(R.id.image_eyes);
        im_Eyes.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == bt_getCode) {
            getCodeType = true;
            phone = et_Phone.getText().toString().trim();
            if (phone.equals("")) {
                Utils.Toast("请输入手机号码");
                return;
            } else if (phone.length() < 11) {
                Utils.Toast("手机号长度至少11位");
                return;
            }
            Map<String, String> map = new HashMap<>();
            map.put("phone", phone);
            new Asyn_SendCode(map).execute();
            time.start();//开始计时
        } else if (v == bt_Next) {
            next();
        } else if (v == im_Eyes) {
            if (password_Show) {
                et_Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                et_Password2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                password_Show = false;
            } else {
                et_Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                et_Password2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                password_Show = true;
            }
        } else if (v == imbt_back) {
            this.finish();
        }
    }

    private void next() {
        phone = et_Phone.getText().toString().trim();
        code = et_Code.getText().toString().trim();
        password = et_Password.getText().toString().trim();
        password2 = et_Password2.getText().toString().trim();
        if (phone.equals("")) {
            Utils.Toast("请输入手机号码");
            return;
        } else if (!getCodeType) {
            Utils.Toast("请先获取验证码");
            return;
        } else if (phone.length() < 11) {
            Utils.Toast("手机号长度至少11位");
            return;
        } else if (code.equals("")) {
            Utils.Toast("请输入验证码");
            return;
        } else if (code.length() < 6) {
            Utils.Toast("验证码为6位纯数字");
            return;
        } else if (password.equals("")) {
            Utils.Toast("请输入密码");
            return;
        } else if (password.length() < 6 || password.length() > 20) {
            Utils.Toast("密码长度应该在6-20位之间");
            return;
        } else if (password2.equals("")) {
            Utils.Toast("请再次输入密码");
            return;
        } else if (!password.equals(password2)) {
            Utils.Toast("两次密码输入不一致，请重新输入");
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("phoneNo", phone);
        map.put("loginPassword", MD5Util.stringMD5(password));
        map.put("code", code);
        new Asyn_Register(map).execute();
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            bt_getCode.setText("重新验证");
            bt_getCode.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            bt_getCode.setClickable(false);
            bt_getCode.setText(millisUntilFinished / 1000 + "秒");
        }
    }
}