package com.example.administrator.qgzs.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/11 0011.
 */

public class PostQueryInfo {
    private String message;
    private String nu;
    private String ischeck;
    private String com;
    private String status;
    private String condition;
    private String state;
    private List<DataBean> data;
    public static class DataBean {
        private String time;
        private String context;
        private String ftime;

        public DataBean() {
        }

        public void setTime(String time) {
            this.time = time;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public void setFtime(String ftime) {
            this.ftime = ftime;
        }

        public String getTime() {
            return time;
        }

        public String getContext() {
            return context;
        }

        public String getFtime() {
            return ftime;
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setNu(String nu) {
        this.nu = nu;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public String getNu() {
        return nu;
    }

    public String getIscheck() {
        return ischeck;
    }

    public String getCom() {
        return com;
    }

    public String getStatus() {
        return status;
    }

    public String getCondition() {
        return condition;
    }

    public String getState() {
        return state;
    }

    public List<DataBean> getData() {
        return data;
    }
}
