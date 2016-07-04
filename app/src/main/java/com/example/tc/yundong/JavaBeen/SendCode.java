package com.example.tc.yundong.JavaBeen;

/**
 * 下发验证码
 * Created by tc on 2016/7/1.
 */
public class SendCode {
    private int status;
    private String data = "";
    private String msg = "";

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
