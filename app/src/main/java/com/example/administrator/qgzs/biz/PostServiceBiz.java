package com.example.administrator.qgzs.biz;

import com.example.administrator.qgzs.bean.PostQueryInfo;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/2/11 0011.
 */

public interface PostServiceBiz {
    @POST("query")
    Observable<PostQueryInfo> searchRx(@Query("type") String type, @Query("postid") String postid);
}
