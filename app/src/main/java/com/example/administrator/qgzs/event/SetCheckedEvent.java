package com.example.administrator.qgzs.event;

/**
 * Created by 陈相宇 on 2017/2/23.
 */

public class SetCheckedEvent {

    Boolean isChecked;
    int position;

    public SetCheckedEvent(Boolean isChecked, int position) {
        this.isChecked = isChecked;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }
}
