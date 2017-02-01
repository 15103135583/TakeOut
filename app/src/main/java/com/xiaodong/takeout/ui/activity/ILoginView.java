package com.xiaodong.takeout.ui.activity;

/**
 * Created by Think on 2017/1/26.
 */

public interface ILoginView {
    void dialogVisiable(boolean flag);
    void showToast(String content);

    void result(boolean b,String data);
}
