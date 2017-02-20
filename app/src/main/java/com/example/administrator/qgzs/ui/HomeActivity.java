package com.example.administrator.qgzs.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.qgzs.R;
import com.example.administrator.qgzs.adapter.HomeAdapter;
import com.example.administrator.qgzs.bean.Goods;
import com.example.administrator.qgzs.persenter.JudgePresenter;
import com.example.administrator.qgzs.utils.DatabaseHelper;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/2/18 0018.
 */
public class HomeActivity extends Activity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.title)
    TextView title;

    Boolean isWork = true;
    List<Goods> list;
    HomeAdapter adapter;

    private DatabaseHelper helper;

    final Handler handler = new Handler();
    Runnable runnable = new Runnable(){
        @Override
        public void run() {
            // TODO Auto-generated method stub
            // 在此处添加执行的代码
            Log.i("Tag","Judge()");
            handler.postDelayed(this, 1000);// 50是延时时长
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        init();
        initData();

    }

    private void init() {
        //初始化recycle view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //数据库
        helper = new DatabaseHelper(this);
        //定时执行
        handler.postDelayed(runnable, 1000);// 打开定时器，执行操作
//        handler.removeCallbacks(runnable);// 关闭定时器处理
    }

    public void initData() {
        try {
            list = helper.readAllGoodsBean();
            adapter = new HomeAdapter(this, list);
            recyclerView.setAdapter(adapter);
        } catch (NullPointerException e) {
            recyclerView.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.buyButton, R.id.addTask, R.id.pause, R.id.start})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buyButton:
                Intent toBuyActivity = new Intent(this, BuyActivity.class);
                startActivity(toBuyActivity);
                overridePendingTransition(R.animator.activity_buy_to_home_in, R.animator.activity_home_to_buy_out);
                break;
            case R.id.addTask:
                Intent toAddTask = new Intent(this, AddActivity.class);
                startActivity(toAddTask);
                overridePendingTransition(R.animator.activity_top_to_bottom_in, R.animator.activity_top_to_bottom_out);
                break;
            case R.id.pause:
                title.setText("已暂停");
                isWork = false;
                break;
            case R.id.start:
                title.setText("检测中");
                isWork = true;
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void Judge() {
        if (isWork) {
            for (Goods goods : list) {
                JudgePresenter.doJudge(goods.getGoodsID());
            }
        }
    }

}
