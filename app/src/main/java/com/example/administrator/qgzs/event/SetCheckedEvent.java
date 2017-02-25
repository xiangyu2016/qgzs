package com.example.administrator.qgzs.event;

/**
 * Created by 陈相宇 on 2017/2/23.
 */

public class SetCheckedEvent {

    private int isChecked;
    private int position;

    public SetCheckedEvent(int isChecked, int position) {
        this.isChecked = isChecked;
        this.position = position;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getIsChecked() {
        return isChecked;
    }

    public int getPosition() {
        return position;
    }
}
