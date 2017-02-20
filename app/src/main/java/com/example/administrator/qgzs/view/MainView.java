package com.example.administrator.qgzs.view;

import com.example.administrator.qgzs.bean.PostQueryInfo;

/**
 * Created by Administrator on 2017/2/11 0011.
 */

public interface MainView  extends BaseView{
    void updateListUI(PostQueryInfo postQueryInfo);
    void errorToast(String message);
}
