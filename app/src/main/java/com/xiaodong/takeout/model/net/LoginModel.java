package com.xiaodong.takeout.model.net;

import android.text.TextUtils;

/**
 * Created by Think on 2017/1/26.
 */
public class LoginModel {

    public boolean login(final String phone, final String pwd){
        if (TextUtils.equals("15103135583",phone) && TextUtils.equals("111",pwd)) {
            return true;
        }
        return false;
    }
}
