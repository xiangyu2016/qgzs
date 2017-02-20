package com.example.administrator.qgzs.persenter;

import com.example.administrator.qgzs.bean.PostQueryInfo;
import com.example.administrator.qgzs.model.PostSearchModel;
import com.example.administrator.qgzs.model.PostSearchModelImpl;
import com.example.administrator.qgzs.view.MainView;

/**
 * Created by Administrator on 2017/2/11 0011.
 */

public class PostPresenter extends BasePersenter<MainView> {
    private PostSearchModel postSearchModel;
    public PostPresenter(MainView mainView){
        attach(mainView);
        postSearchModel = new PostSearchModelImpl();
    }
    public void requestHomeData(String type,String postid){
        if(postSearchModel == null||mView == null)
            return;
        mView.showProgressDialog();
        postSearchModel.requestPostSearch(type, postid, new PostSearchModel.PostSearchCallback() {
            @Override
            public void requestPostSearchSuccess(PostQueryInfo postQueryInfo) {
                mView.hideProgressDialog();
                if(postQueryInfo!=null&&"ok".equals(postQueryInfo.getMessage())) {
                    mView.updateListUI(postQueryInfo);
                }
            }
            @Override
            public void requestPostSearchFail(String failStr) {
                mView.hideProgressDialog();
                mView.errorToast(failStr);
            }
        });
    }

}
