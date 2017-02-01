package com.xiaodong.takeout;

import android.app.Application;
import android.content.Context;

/**
 * Created by Think on 2017/1/31.
 */

public class MyApplication extends Application {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
