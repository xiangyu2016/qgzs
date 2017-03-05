package com.example.administrator.qgzs.persenter;

import android.util.Log;
import com.example.administrator.qgzs.MainAPP;
import com.example.administrator.qgzs.bean.BooleanBean;
import com.example.administrator.qgzs.bean.Goods;
import com.example.administrator.qgzs.bean.PriceBean;
import com.example.administrator.qgzs.event.BingoEvent;
import com.example.administrator.qgzs.event.InitEvent;
import com.example.administrator.qgzs.utils.BuyDatabaseHelper;
import com.example.administrator.qgzs.utils.DatabaseHelper;
import com.example.administrator.qgzs.utils.HttpApi;
import com.example.administrator.qgzs.utils.RetrofitUtils;

import org.greenrobot.eventbus.EventBus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/2/19 0019.
 */

public class JudgePresenter {

    public  static void doJudge(final Goods bean) {
        //秒杀判断
        if(bean.getMiaosha()==1){
            Thread thread=new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    // TODO Auto-generated method stub
                    try {
                        //从一个URL加载一个Document对象。
                        //3243688
                        Document doc = Jsoup.connect("https://item.jd.com/"+bean.getGoodsID()+".html").get();
                        //选择“秒杀”所在节点
                        Elements elements = doc.select("div.activity-type");
                        //判断
                        if(elements.text().endsWith("秒杀")){
                            Bingo(bean);
                        }
                    }catch(Exception e) {
                        Log.i("Tag", e.toString());
                    }
                }
            });
            thread.start();
        }


        //单价判断
        if(bean.getDanjia()==1){
            Log.i("bean.getName()",bean.getName());
            Retrofit retrofit=RetrofitUtils.getInstance().getRetrofit();
            HttpApi httpApi=retrofit.create(HttpApi.class);
            Call<List<PriceBean>> call=httpApi.getPrice("J_"+bean.getGoodsID());
            call.enqueue(new Callback<List<PriceBean>>() {
                @Override
                public void onResponse(Call<List<PriceBean>> call, Response<List<PriceBean>> response) {
                    List<PriceBean> list=response.body();
                    Log.i("Tag", list.get(0).getP());
                    if(Float.parseFloat(bean.getPrice())>=Float.parseFloat(list.get(0).getP())&&Float.parseFloat(list.get(0).getP())>0){
                        Log.i("Tag", "true");
                        Bingo(bean);
                    }
                }
                @Override
                public void onFailure(Call<List<PriceBean>> call, Throwable t) {

                }
            });
        }
        //添加商品名称
        if(bean.getName().equals("")){
            Log.i("bean.getName()",bean.getName());
            Thread thread=new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    // TODO Auto-generated method stub
                    try {
                        //从一个URL加载一个Document对象。
                        //3243688
                        Document doc = Jsoup.connect("https://item.jd.com/"+bean.getGoodsID()+".html").get();
                        //选择“秒杀”所在节点
                        Elements elements = doc.head().select("title");
                        //设置商品名称
                        bean.setName(elements.text());
                        //更新数据库
                        upDateBean(bean);
                    }catch(Exception e) {
                        Log.i("Tag", e.toString());
                    }
                }
            });
            thread.start();
        }
        //是否可以使用京东券


    }

    public static void Bingo( Goods bean){
        //更新数据库
        DatabaseHelper helper = new DatabaseHelper(MainAPP.app);
        bean.setBingo(1);
        bean.setIsChecked(0);
        helper.updateAGoods(bean);
        //
        BuyDatabaseHelper buyHelper = new BuyDatabaseHelper(MainAPP.app);
        buyHelper.insert(bean);
        //更新UI
        EventBus.getDefault().post(new BingoEvent("Bingo!"));
    }

    public static  void upDateBean(Goods bean){
        //更新数据库
        DatabaseHelper helper = new DatabaseHelper(MainAPP.app);
        helper.updateAGoods(bean);
        //更新UI
        EventBus.getDefault().post(new InitEvent("SetName!"));
    }

}
