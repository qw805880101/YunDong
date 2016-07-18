package com.example.tc.yundong.JavaBeen;

import java.util.List;

/**
 * Created by admin on 2016/7/14.
 */
public class HomeData {

    private int status; // 1 成功  0 失败
    private String msg; // 返回日志
    private int rows;
    private int total; //总条数

    List<StartPicture> startPicture;
    List<Stadiums> stadiums;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Stadiums> getStadiums() {
        return stadiums;
    }

    public void setStadiums(List<Stadiums> stadiums) {
        this.stadiums = stadiums;
    }

    public List<StartPicture> getStartPicture() {
        return startPicture;
    }

    public void setStartPicture(List<StartPicture> startPicture) {
        this.startPicture = startPicture;
    }
}
