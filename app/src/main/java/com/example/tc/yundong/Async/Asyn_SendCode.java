package com.example.tc.yundong.Async;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.tc.yundong.Http.HttpUtils;
import com.example.tc.yundong.JavaBeen.SendCode;
import com.example.tc.yundong.Util.Info;
import com.example.tc.yundong.Util.Utils;

import java.util.Map;

/**
 * Created by tc on 2016/7/1.
 */
public class Asyn_SendCode extends AsyncTask<Integer, Integer, SendCode> {
    private String url = "";

    public Asyn_SendCode(Map<String, String> map) {
        url = Info.Url + "user/sendCode.do?";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            url += entry.getKey() + "=" + entry.getValue() + "&";
        }
        url += "key" + Info.key;
        Utils.Log("url = " + url);
    }

    protected SendCode doInBackground(Integer... params) {

        return HttpUtils.getJsonStr(url, SendCode.class);
    }

    protected void onPostExecute(SendCode sendCode) {
        if (sendCode != null) {
            if (sendCode.getStatus() == 0) { //获取验证码失败
                Toast.makeText(Info.currentActivity, sendCode.getMsg(), Toast.LENGTH_SHORT).show();
                return;
            } else if (sendCode.getStatus() == 1) { //获取验证码成功
                Toast.makeText(Info.currentActivity, sendCode.getMsg(), Toast.LENGTH_SHORT).show();
            }
        } else {
            if (!Info.Erro.equals("")) {
                Toast.makeText(Info.currentActivity, Info.Erro, Toast.LENGTH_SHORT).show();
                Utils.Log("错误~~~~~~~~~~~~~~~~~" + Info.Erro);
            }
        }
    }
}
