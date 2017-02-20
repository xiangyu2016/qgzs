package com.example.administrator.qgzs.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.administrator.qgzs.view.BaseView;

/**
 * Created by Administrator on 2017/2/11 0011.
 */

public class BaseActivity extends Activity implements BaseView {

    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("查询中...");
    }
    @Override
    public void showProgressDialog(){
        if(progressDialog!=null){
            progressDialog.show();
        }
    }
    @Override
    public void hideProgressDialog(){
        if(progressDialog!=null&&progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

}
