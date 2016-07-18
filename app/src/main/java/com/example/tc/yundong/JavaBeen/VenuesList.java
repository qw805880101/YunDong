package com.example.tc.yundong.JavaBeen;

import java.util.List;

/**
 * Created by admin on 2016/7/17.
 */
public class VenuesList {

    private int total; //总条数
    private int status; //状态 1成功 0 失败
    private int page; //当前页
    private List<Stadiums> data;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Stadiums> getData() {
        return data;
    }

    public void setData(List<Stadiums> data) {
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
