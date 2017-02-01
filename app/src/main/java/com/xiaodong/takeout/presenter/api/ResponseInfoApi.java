package com.xiaodong.takeout.presenter.api;

import com.xiaodong.takeout.model.net.bean.ResponseInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Think on 2017/1/27.
 * 对请求方式和请求参数的封装
 */

public interface ResponseInfoApi {
    /**
     * 用户登陆:/login?phone="15103135583"&password="bj"
     * 注：ResponseInfo是服务器回复数据封装成的对象。
     */
    @GET(Constant.LOGIN)
    Call<ResponseInfo> login(
            @Query("phone")String phone,
            @Query("password")String password,
            @Query("type")String type);
}
