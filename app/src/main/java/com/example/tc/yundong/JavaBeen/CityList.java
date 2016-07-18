package com.example.tc.yundong.JavaBeen;

import java.util.List;

/**
 *
 * 城市列表
 * Created by tc on 2016/7/18.
 */
public class CityList {

    private int status; //状态
    private String msg;
    private List<City> cities;

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

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    class City {
        private int totalRows;
        private int pageSize;
        private int page;
        private int totalPages;
        private int startRow;
        private int rows;
        private int orderField;
        private int searchVal;
        private int orde;
        private int cityID;
        private String name;

        public int getTotalRows() {
            return totalRows;
        }

        public void setTotalRows(int totalRows) {
            this.totalRows = totalRows;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
        }

        public int getOrderField() {
            return orderField;
        }

        public void setOrderField(int orderField) {
            this.orderField = orderField;
        }

        public int getSearchVal() {
            return searchVal;
        }

        public void setSearchVal(int searchVal) {
            this.searchVal = searchVal;
        }

        public int getOrde() {
            return orde;
        }

        public void setOrde(int orde) {
            this.orde = orde;
        }

        public int getCityID() {
            return cityID;
        }

        public void setCityID(int cityID) {
            this.cityID = cityID;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
