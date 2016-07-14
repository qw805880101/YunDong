package com.example.tc.yundong.JavaBeen;

/**
 * Created by admin on 2016/7/14.
 */
public class StartPicture {
    private int pictureNo; //图片序号
    private String pictureType; //图片类型 0：带入页，1：启动页，2：广告页，3：指导页
    private String picture; //图片路径
    private int sort; //图片显示次序
    private String showTime; //展示时间
    private String showWay; //展示方式 0:自动，1：手动

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getPictureNo() {
        return pictureNo;
    }

    public void setPictureNo(int pictureNo) {
        this.pictureNo = pictureNo;
    }

    public String getPictureType() {
        return pictureType;
    }

    public void setPictureType(String pictureType) {
        this.pictureType = pictureType;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getShowWay() {
        return showWay;
    }

    public void setShowWay(String showWay) {
        this.showWay = showWay;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
