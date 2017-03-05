package com.example.administrator.qgzs.bean;

/**
 * Created by Administrator on 2017/2/19 0019.
 */

public class Goods {
    private String id;
    private String goodsID;
    private String price;

    private int miaosha;
    private int danjia;

    private int isChecked;
    private int bingo;

    private String name;
    private int jdCoupon;


    public Goods() {
        isChecked=BooleanBean.TRUE;
        bingo=BooleanBean.FALSE;
        jdCoupon=BooleanBean.FALSE;
    }

    public String getName() {
        return name;
    }

    public int getJdCoupon() {
        return jdCoupon;
    }

    public void setJdCoupon(int jdCoupon) {
        this.jdCoupon = jdCoupon;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setMiaosha(int miaosha) {
        this.miaosha = miaosha;
    }

    public void setDanjia(int danjia) {
        this.danjia = danjia;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }

    public void setBingo(int bingo) {
        this.bingo = bingo;
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

    public int getMiaosha() {
        return miaosha;
    }

    public int getDanjia() {
        return danjia;
    }

    public int getIsChecked() {
        return isChecked;
    }

    public int getBingo() {
        return bingo;
    }
}
