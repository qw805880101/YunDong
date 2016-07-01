package com.example.tc.yundong.Async;

import android.os.AsyncTask;
import android.widget.Toast;

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
        for (Map.Entry<String, String> entry : map.entrySet()) {
            url += entry.getKey() + "=" + entry.getValue() + "&";
        }
        url += "key" + Info.key;
        Utils.Log("url = " + url);
    }

    protected Register doInBackground(Integer... params) {

        return HttpUtils.getJsonStr(url, Register.class);
    }

    protected void onPostExecute(Register register) {
        if (register != null) {
            if (register.getStatus() == 0) { //注册失败
                Toast.makeText(Info.currentActivity, register.getMsg(), Toast.LENGTH_SHORT).show();
                return;
            } else if (register.getStatus() == 1) { //注册成功
                Toast.makeText(Info.currentActivity, register.getMsg(), Toast.LENGTH_SHORT).show();
            }
        } else {
            if (!Info.Erro.equals("")) {
                Toast.makeText(Info.currentActivity, Info.Erro, Toast.LENGTH_SHORT).show();
                Utils.Log("错误~~~~~~~~~~~~~~~~~" + Info.Erro);
            }
        }
    }
}