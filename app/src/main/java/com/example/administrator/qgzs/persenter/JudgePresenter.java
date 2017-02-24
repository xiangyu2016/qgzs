package com.example.administrator.qgzs.persenter;

import android.util.Log;
import com.example.administrator.qgzs.MainAPP;
import com.example.administrator.qgzs.bean.Goods;
import com.example.administrator.qgzs.bean.PriceBean;
import com.example.administrator.qgzs.event.BingoEvent;
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

    public  static void doJudge(final String ID, final String goodsID,final String price) {

        Thread thread=new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                // TODO Auto-generated method stub
                try {
                    //从一个URL加载一个Document对象。
                    //3243688
                    Document doc = Jsoup.connect("https://item.jd.com/"+goodsID+".html").get();
                    //选择“秒杀”所在节点
                    Elements elements = doc.select("div.activity-type");
                    //判断
                    if(elements.text().endsWith("秒杀")){
                        Bingo(ID,goodsID,price);
                    }
                }catch(Exception e) {
                    Log.i("Tag", e.toString());
                }

            }
        });
        thread.start();

        Retrofit retrofit=RetrofitUtils.getInstance().getRetrofit();
        HttpApi httpApi=retrofit.create(HttpApi.class);
        Call<List<PriceBean>> call=httpApi.getPrice("J_"+goodsID);
        call.enqueue(new Callback<List<PriceBean>>() {
            @Override
            public void onResponse(Call<List<PriceBean>> call, Response<List<PriceBean>> response) {
                List<PriceBean> list=response.body();
                Log.i("Tag", list.get(0).getP());
                if(Float.parseFloat(price)>=Float.parseFloat(list.get(0).getP())){
                    Log.i("Tag", "true");
                    Bingo(ID,goodsID,price);
                }
            }

            @Override
            public void onFailure(Call<List<PriceBean>> call, Throwable t) {

            }
        });

    }

    public static void Bingo( String ID, String goodsID, String price){

//        //删除
//        DatabaseHelper helper=new DatabaseHelper(MainAPP.app);
//        helper.deleteAGoodsBean(Integer.parseInt(ID));
//        //添加
//        BuyDatabaseHelper buyHelper=new BuyDatabaseHelper(MainAPP.app);
//        Goods goods=new Goods(goodsID,price);
//        buyHelper.insert(goods);
        //更新UI
        EventBus.getDefault().post(new BingoEvent("Bingo!"));
    }
}
