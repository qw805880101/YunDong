package com.example.tc.yundong.Async;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.tc.yundong.Http.HttpUtils;
import com.example.tc.yundong.JavaBeen.HomeData;
import com.example.tc.yundong.JavaBeen.SendCode;
import com.example.tc.yundong.Util.Info;
import com.example.tc.yundong.Util.Utils;

import java.util.Map;

/**
 * Created by tc on 2016/7/1.
 */
public class Asyn_GetHomeData extends AsyncTask<Integer, Integer, HomeData> {
    private String url = "";
    private Handler mHandler;

    public Asyn_GetHomeData(Handler mHandler) {
        this.mHandler = mHandler;
        url = Info.Url + "stadium/firstPage.do?";
        url = Utils.getUrl(url, null);
        Utils.Log("url = " + url);
    }

    protected HomeData doInBackground(Integer... params) {

        return HttpUtils.getJsonStr(url, HomeData.class);
    }

    protected void onPostExecute(HomeData homeData) {
        if (homeData != null) {
            Message message = new Message();
            message.obj = homeData;
            message.what = 0;
            mHandler.sendMessage(message);

        } else {
            if (!Info.Erro.equals("")) {
                Utils.Toast(Info.Erro);
                Utils.Log("错误~~~~~~~~~~~~~~~~~" + Info.Erro);
            }
        }
    }
}
