package com.example.tc.yundong.JavaBeen;

/**
 * 场地简况
 * Created by tc on 2016/7/14.
 */
public class Stadiums {

    private String stadiumID; // 场馆编号
    private String name; //名称
    private int cityId; //城市
    private int areaId; //区域范围
    private String description; //场馆介绍
    private String phone; //场馆联系电话
    private String address; //地址
    private String y; //东经坐标
    private String x; //北纬坐标
    private String area; //区域
    private String pic; //显示图片
    private int avgprice; // 价格

    public int getAvgprice() {
        return avgprice;
    }

    public void setAvgprice(int avgprice) {
        this.avgprice = avgprice;
    }

    public String getStadiumID() {
        return stadiumID;
    }

    public void setStadiumID(String stadiumID) {
        this.stadiumID = stadiumID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}
