package com.example.administrator.qgzs.ui;

import android.app.Activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Handler;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.qgzs.R;
import com.example.administrator.qgzs.adapter.HomeAdapter;
import com.example.administrator.qgzs.bean.Goods;
import com.example.administrator.qgzs.event.BingoEvent;
import com.example.administrator.qgzs.event.SetCheckedEvent;
import com.example.administrator.qgzs.persenter.JudgePresenter;
import com.example.administrator.qgzs.utils.DatabaseHelper;
import com.example.administrator.qgzs.utils.VibratorUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
    private MediaPlayer mPlayer;

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
        handler.postDelayed(runnable,4000);
        //在当前界面注册一个订阅者
        EventBus.getDefault().register(this);
        //声音
        mPlayer = MediaPlayer.create(this,R.raw.ring);



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
//                Intent toBuyActivity = new Intent(this, BuyActivity.class);
//                startActivity(toBuyActivity);
//                overridePendingTransition(R.animator.activity_buy_to_home_in, R.animator.activity_home_to_buy_out);
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

                if(goods.getChecked())
                    JudgePresenter.doJudge(goods.getId(),goods.getGoodsID(),goods.getPrice());
            }
        }
    }

    Handler handler=new Handler();
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            Judge();
            handler.postDelayed(this, 5*60*1000);
        }
    };

    public AlertDialog alertDialog;
    public void Bingo(){
        //震动
        VibratorUtil.Vibrate(this, 500); //震动100ms  
        //弹框
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bingo");
        builder.setMessage("查询到符合条件的商品啦！快去查看把！");
        builder.setPositiveButton("知道啦", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog = builder.show();

        //铃声
        mPlayer.start();

        //通知
        Intent intent = new Intent(this, HomeActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.buy_icon)
                .setTicker("有新的商品符合条件啦！")
                .setContentTitle("抢购助手")
                .setContentText("有新的商品符合条件啦！点击查看")
                .setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_SOUND)
                .setContentIntent(contentIntent)
                .setContentInfo("Info");
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());

        //刷新数据
        initData();

    }

    @Subscribe //订阅事件 FirstEvent
    public void onEventMainThread(BingoEvent event){
       Bingo();
    }

    @Subscribe //订阅事件
    public void onEventSetIsChecked(SetCheckedEvent event){
        list.get(event.getPosition()).setChecked(event.getChecked());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消注册
    }
}
