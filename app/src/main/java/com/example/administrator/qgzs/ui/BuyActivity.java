package com.example.administrator.qgzs.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.administrator.qgzs.R;
import com.example.administrator.qgzs.adapter.BuyAdapter;
import com.example.administrator.qgzs.bean.Goods;
import com.example.administrator.qgzs.utils.BuyDatabaseHelper;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/2/19 0019.
 */

public class BuyActivity extends Activity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    List<Goods> list;
    BuyAdapter adapter;

    private BuyDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        //初始化recycle view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //数据库
        helper = new BuyDatabaseHelper(this);
        initData();
    }

    public void initData() {
        try{
            list=helper.readAllBuyGoodsBean();
            adapter=new BuyAdapter(this,list);
            recyclerView.setAdapter(adapter);
        }catch (NullPointerException e){
            recyclerView.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.buyButton)
    public void onClick() {
       Finish();
    }

    private void Finish(){
        finish();
        overridePendingTransition(R.animator.activity_home_to_buy_in,R.animator.activity_buy_to_home_out);
    }
}
