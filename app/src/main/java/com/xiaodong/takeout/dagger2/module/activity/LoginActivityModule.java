package com.xiaodong.takeout.dagger2.module.activity;

import com.xiaodong.takeout.presenter.activity.LoginPresenter;
import com.xiaodong.takeout.ui.activity.LoginActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Think on 2017/1/26.
 */
@Module
public class LoginActivityModule {
    private LoginActivity activity;

    public LoginActivityModule(LoginActivity activity) {
        this.activity = activity;
    }

    @Provides
    LoginPresenter providesLoginPresenter(){
        return new LoginPresenter(activity);
    }
}
