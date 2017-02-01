package com.xiaodong.takeout.dagger2.componet.activity;

import com.xiaodong.takeout.dagger2.module.activity.LoginActivityModule;
import com.xiaodong.takeout.ui.activity.LoginActivity;

import dagger.Component;

/**
 * Created by Think on 2017/1/26.
 */
@Component(modules = LoginActivityModule.class)
public interface LoginComponet {
    void in(LoginActivity activity);
}
