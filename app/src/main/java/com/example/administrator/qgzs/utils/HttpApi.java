package com.example.administrator.qgzs.utils;

import com.example.administrator.qgzs.bean.Goods;
import com.example.administrator.qgzs.bean.PriceBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/2/19 0019.
 */

public interface HttpApi {

    //获取商品价格
    @GET("get")
    Call<List<PriceBean>> getPrice(
            @Query("skuid") String skuid
    );
}
