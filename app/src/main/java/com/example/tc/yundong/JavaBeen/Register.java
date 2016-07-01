package com.example.tc.yundong.JavaBeen;

/**
 * Created by tc on 2016/7/1.
 */
public class Register {

    private int status; // 1=成功  0=失败
    private String msg = "";

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
