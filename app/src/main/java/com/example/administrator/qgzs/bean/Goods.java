package com.example.administrator.qgzs.bean;

/**
 * Created by Administrator on 2017/2/19 0019.
 */

public class Goods {
    String id;
    String goodsID;
    String price;

    public Goods() {
    }

    public Goods( String goodsID, String price) {
        this.goodsID = goodsID;
        this.price = price;
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
