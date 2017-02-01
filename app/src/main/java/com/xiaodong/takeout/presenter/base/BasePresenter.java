package com.xiaodong.takeout.presenter.base;

import com.xiaodong.takeout.model.dao.DBHelper;
import com.xiaodong.takeout.model.net.bean.ResponseInfo;
import com.xiaodong.takeout.presenter.api.Constant;
import com.xiaodong.takeout.presenter.api.ErrorInfo;
import com.xiaodong.takeout.presenter.api.ResponseInfoApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Think on 2017/1/30.
 */

public abstract class BasePresenter {
    //数据库

    //使用静态变量,让子类可以方便访问
    protected static ResponseInfoApi responseInfoApi;

    protected DBHelper dbHelper;

    public BasePresenter() {

        // 网络访问：
        // 第一步：创建Builder，指定baseUrl和数据解析工具
//    Retrofit.Builder builder = new Retrofit.Builder();
//    builder.baseUrl(Constant.BASEURL);
//    builder.addConverterFactory(GsonConverterFactory.create());// Gson解析
//
//    // // 第二步：创建Retrofit
////    Retrofit retrofit = builder.build();

        //保证Retrofit第一次初始化后所有子类都可以使用
        if (responseInfoApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            responseInfoApi = retrofit.create(ResponseInfoApi.class);
        }

        //获取数据库帮助类的实例
        dbHelper.getInstance();
    }

    public class CallbackAdapter implements Callback<ResponseInfo> {

        @Override
        public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
            // 处理回复
            if (response != null && response.isSuccessful()) {
                ResponseInfo info = response.body();

                if("0".equals(info.code)){
                    // 服务器端处理成功，并返回目标数据
                    parserData(info.data);
                }else{
                    // 服务器端处理成功，返回错误提示，该信息需要展示给用户
                    // 依据code值获取到失败的数据
                    String msg = ErrorInfo.INFO.get(info.code);
                    failed(msg);
                }

            } else {
                // 联网过程中的异常
            }


        }

        @Override
        public void onFailure(Call<ResponseInfo> call, Throwable t) {
            // 联网过程中的异常
        }
    }

    /**
     * 错误处理
     * @param msg
     */
    protected abstract void failed(String msg);

    /**
     * 解析服务器回复数据
     * @param data
     */
    protected abstract void parserData(String data);
}
