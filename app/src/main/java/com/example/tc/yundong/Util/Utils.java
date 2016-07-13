package com.example.tc.yundong.Util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Utils {
    private final static String TAG = "tcc";
    private static Context mApplicationContent;

    public static void initialize(Application app){
        mApplicationContent = app.getApplicationContext();
    }

    public static void Log(String msg) {
        android.util.Log.i(TAG, msg);
    }

    public static void Toast(String str){
        Toast.makeText(mApplicationContent, str, Toast.LENGTH_SHORT).show();
    }

    /**
     * 对网络连接状态进行判断
     *
     * @return true, 可用； false， 不可用
     */
    public static boolean isOpenNetwork() {
        ConnectivityManager connManager = (ConnectivityManager) Info.currentActivity
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }

    /**
     * 关闭输入法
     * @param act
     */
    public static void closeInputMethod(Activity act){
        View view = act.getCurrentFocus();
        if(view!=null){
            ((InputMethodManager)mApplicationContent.getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    /**
     * 图片保存
     *
     * @param imgFile   保存文件名
     * @param byteArray 图片数据
     */
    public static void saveImage(File imgFile, byte[] byteArray) {
        FileOutputStream fos = null;
        try {
            imgFile.delete();
            fos = new FileOutputStream(imgFile);
            fos.write(byteArray);
            fos.flush();
            fos.close();
            Utils.Log("图片存储成功");
        } catch (IOException e) {
            Utils.Log("图片存储错误~~~~~~~~~~~~~~~~~" + e);
        } finally {
            if (fos != null) {
                fos = null;
            }
        }
    }

    /**
     * 获取当前时间
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getTodayNews() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * 日期转换
     * @param str 日期，格式为String："20130903";
     * @return
     */
    public static String StrToDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        String str2 = null;
        try {
            date = format.parse(str);
            format.applyPattern("yyyy年MM月dd日");
            str2 = format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str2;
    }

    /**
     * 获取指定日后 前dayAddNum 天的 日期
     *
     * @param day       日期，格式为String："2013-9-3";
     * @param dayAddNum 增加天数 格式为int;
     * @return
     */
    public static String getDateStr(String day, int dayAddNum) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date nowDate = null;
        try {
            nowDate = df.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date newDate2 = new Date(nowDate.getTime() - dayAddNum * 24 * 60 * 60 * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateOk = simpleDateFormat.format(newDate2);
        return dateOk;
    }

    /**
     * 获取url
     */
    public static String getUrl(String url, Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            url += entry.getKey() + "=" + entry.getValue() + "&";
        }
        url += "key" + Info.key;
        return url;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param context
     * @param dpValue 要转换的dp值
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param context
     * @param pxValue 要转换的px值
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
