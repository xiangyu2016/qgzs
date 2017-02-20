package com.example.administrator.qgzs.persenter;



/**
 * Created by Administrator on 2017/2/11 0011.
 */

public abstract class BasePersenter<T> {

    public T mView;
    public void attach(T view){
        this.mView = view;
    }
    public void detach(){
        mView = null;
    }
}
