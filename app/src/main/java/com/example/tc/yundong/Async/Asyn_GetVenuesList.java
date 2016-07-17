package com.example.tc.yundong.Async;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.example.tc.yundong.Http.HttpUtils;
import com.example.tc.yundong.JavaBeen.HomeData;
import com.example.tc.yundong.JavaBeen.VenuesList;
import com.example.tc.yundong.Util.Info;
import com.example.tc.yundong.Util.Utils;

import java.util.Map;

/**
 * 获取场馆列表
 * Created by tc on 2016/7/1.
 */
public class Asyn_GetVenuesList extends AsyncTask<Integer, Integer, VenuesList> {
    private String url = "";
    private Handler mHandler;

    public Asyn_GetVenuesList(Map<String, String> map, Handler mHandler) {
        this.mHandler = mHandler;
        url = Info.Url + "stadium/list.do?";
        url = Utils.getUrl(url, map);
        Utils.Log("url = " + url);
    }

    protected VenuesList doInBackground(Integer... params) {

        return HttpUtils.getJsonStr(url, VenuesList.class);
    }

    protected void onPostExecute(VenuesList venuesList) {
        if (venuesList != null) {
            if (venuesList.getStatus() == 1) { //获取成功
                Message message = new Message();
                message.obj = venuesList;
                message.what = 1;
                mHandler.sendMessage(message);
            } else { //获取失败
                Utils.Toast(venuesList.getMsg());
            }

        } else {
            if (!Info.Erro.equals("")) {
                Utils.Toast(Info.Erro);
                Utils.Log("错误~~~~~~~~~~~~~~~~~" + Info.Erro);
            }
        }
    }
}
