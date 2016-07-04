package com.example.tc.yundong.Async;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.tc.yundong.Http.HttpUtils;
import com.example.tc.yundong.JavaBeen.UserLogin;
import com.example.tc.yundong.Util.Info;
import com.example.tc.yundong.Util.Utils;

import java.util.Map;

/**
 * Created by tc on 2016/7/1.
 */
public class Asyn_Login extends AsyncTask<Integer, Integer, UserLogin> {
    private String url = "";

    public Asyn_Login(Map<String, String> map) {
        url = Info.Url + "user/reg.do?";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            url += entry.getKey() + "=" + entry.getValue() + "&";
        }
        url += "key" + Info.key;
        Utils.Log("url = " + url);
    }

    protected UserLogin doInBackground(Integer... params) {

        return HttpUtils.getJsonStr(url, UserLogin.class);
    }

    protected void onPostExecute(UserLogin userLogin) {
        if (userLogin != null) {

        } else {
            if (!Info.Erro.equals("")) {
                Toast.makeText(Info.currentActivity, Info.Erro, Toast.LENGTH_SHORT).show();
                Utils.Log("错误~~~~~~~~~~~~~~~~~" + Info.Erro);
            }
        }
    }
}