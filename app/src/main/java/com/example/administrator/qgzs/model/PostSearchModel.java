package com.example.administrator.qgzs.model;

import com.example.administrator.qgzs.bean.PostQueryInfo;

/**
 * Created by Administrator on 2017/2/11 0011.
 */

public interface PostSearchModel {
    /**
     * 请求快递信息
     * @param type 快递类型
     * @param postid 快递单号
     * @param callback 结果回调
     */
    void requestPostSearch(String type,String postid,PostSearchCallback callback);
    interface PostSearchCallback{
        void requestPostSearchSuccess(PostQueryInfo postQueryInfo);
        void requestPostSearchFail(String failStr);
    }

}
