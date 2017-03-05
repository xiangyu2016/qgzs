package com.example.administrator.qgzs.event;

/**
 * Created by Administrator on 2017/3/5 0005.
 */

public class InitEvent {

    private String msg;

    public InitEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
