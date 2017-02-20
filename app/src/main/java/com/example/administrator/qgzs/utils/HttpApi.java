package com.example.administrator.qgzs.utils;

import com.example.administrator.qgzs.bean.Goods;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/2/19 0019.
 */

public interface HttpApi {

    //获取Banner信息
    @FormUrlEncoded
    @POST("index.php?s=/api/account/bannerlist")
    Call<Goods> getBannerList(
            @Field("open_id") String open_id
    );
}
