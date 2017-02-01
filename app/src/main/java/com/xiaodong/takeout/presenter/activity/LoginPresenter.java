package com.xiaodong.takeout.presenter.activity;

import android.app.Activity;

import com.xiaodong.takeout.model.net.bean.ResponseInfo;
import com.xiaodong.takeout.presenter.base.BasePresenter;
import com.xiaodong.takeout.ui.activity.LoginActivity;

import retrofit2.Call;

/**
 * Created by Think on 2017/1/26.
 */

public class LoginPresenter extends BasePresenter{
    private LoginActivity activity;

    public LoginPresenter(Activity activity) {
        this.activity = (LoginActivity) activity;
    }

    public void login(final String phone, final String pwd,String type) {
        Call<ResponseInfo> call = responseInfoApi.login(phone, pwd,type);
        call.enqueue(new CallbackAdapter());
    }

    @Override
    protected void failed(String msg) {
        activity.result(false,msg);
    }

    @Override
    protected void parserData(String data) {
        activity.result(true,data);
    }
}
