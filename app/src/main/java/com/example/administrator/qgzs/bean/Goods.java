package com.example.administrator.qgzs.bean;

/**
 * Created by Administrator on 2017/2/19 0019.
 */

public class Goods {
    String id;
    String goodsID;
    String price;
    Boolean isChecked;

    Boolean miaosha;
    Boolean danjia;

    Boolean bingo;

    public Goods() {
    }

    public Goods(String goodsID, String price, Boolean miaosha, Boolean danjia) {
        this.goodsID = goodsID;
        this.price = price;
        this.miaosha = miaosha;
        this.danjia = danjia;
        isChecked=true;
        bingo=false;
    }

    public void setBingo(Boolean bingo) {
        this.bingo = bingo;
    }

    public Boolean getBingo() {
        return bingo;
    }

    public void setMiaosha(Boolean miaosha) {
        this.miaosha = miaosha;
    }

    public void setDanjia(Boolean danjia) {
        this.danjia = danjia;
    }

    public Boolean getMiaosha() {
        return miaosha;
    }

    public Boolean getDanjia() {
        return danjia;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public String getId() {
        return id;
    }

    public String getGoodsID() {
        return goodsID;
    }

    public String getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
