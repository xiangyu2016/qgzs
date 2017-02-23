package com.example.administrator.qgzs.event;

/**
 * Created by 陈相宇 on 2017/2/21.
 */

public class BingoEvent {
    private String msg;

    public BingoEvent(String msg) {//事件传递参数
        this.msg = msg;
    }

    public String getMsg() {//取出事件参数
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
