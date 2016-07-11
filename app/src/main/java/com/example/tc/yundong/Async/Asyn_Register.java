package com.example.tc.yundong.Async;

import android.os.AsyncTask;

import com.example.tc.yundong.Http.HttpUtils;
import com.example.tc.yundong.JavaBeen.Register;
import com.example.tc.yundong.Util.Info;
import com.example.tc.yundong.Util.Utils;

import java.util.Map;

/**
 * 注册
 * Created by tc on 2016/7/1.
 */
public class Asyn_Register extends AsyncTask<Integer, Integer, Register> {
    private String url = "";

    public Asyn_Register(Map<String, String> map) {
        url = Info.Url + "user/reg.do?";
        url = Utils.getUrl(url, map);
        Utils.Log("url = " + url);
    }

    protected Register doInBackground(Integer... params) {

        return HttpUtils.getJsonStr(url, Register.class);
    }

    protected void onPostExecute(Register register) {
        if (register != null) {
            if (register.getStatus() == 0) { //注册失败
                Utils.Toast(register.getMsg());
                return;
            } else if (register.getStatus() == 1) { //注册成功
                Utils.Toast(register.getMsg());

            }
        } else {
            if (!Info.Erro.equals("")) {
                Utils.Toast(Info.Erro);
                Utils.Log("错误~~~~~~~~~~~~~~~~~" + Info.Erro);
            }
        }
    }
}