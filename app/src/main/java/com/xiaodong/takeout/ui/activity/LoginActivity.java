package com.xiaodong.takeout.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.xiaodong.takeout.R;
import com.xiaodong.takeout.dagger2.componet.activity.DaggerLoginComponet;
import com.xiaodong.takeout.dagger2.module.activity.LoginActivityModule;
import com.xiaodong.takeout.presenter.activity.LoginPresenter;
import com.xiaodong.takeout.presenter.api.Constant;
import com.xiaodong.takeout.utils.PrefUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends BaseActivity implements View.OnClickListener,ILoginView {

    @InjectView(R.id.editText)
    EditText editText;
    @InjectView(R.id.editText3)
    EditText editText3;
    @InjectView(R.id.toLogin)
    Button toLogin;
    @InjectView(R.id.save_user)
    CheckBox saveUser;
    private String mPhone;
    private String mPwd;
    private ProgressDialog dialog;

    @Inject
   LoginPresenter presenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        //判断用户是否登陆过,如果已经登陆过的话直接跳转到主页
        if (PrefUtils.getBoolean(this,Constant.IS_LOGIN,false)){
            startActivity();
        }
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        // 此处会实例化具体P层的对象，一旦new出来了，两层之间也就耦合到一起了。
//         presenter = new MainActivityPresenter(this);
        DaggerLoginComponet componet = (DaggerLoginComponet) DaggerLoginComponet.builder()
                .loginActivityModule(new LoginActivityModule(this))
                .build();
        componet.in(this);



        //获取用户名和密码并设置
        String usetNameAndPwd = PrefUtils.getString(this, Constant.USERNAME_AND_PASSWORD, null);
        if (usetNameAndPwd != null){
            String[] str = usetNameAndPwd.split("&");
            editText.setText(str[0]);
            editText3.setText(str[1]);
        }
        dialog = new ProgressDialog(this);
        toLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toLogin:
                mPhone = editText.getText().toString().trim();
                mPwd = editText3.getText().toString().trim();
                boolean userInfo = checkUserInfo(mPhone, mPwd);
                if (userInfo){
                    dialogVisiable(true);
                    presenter.login(mPhone,mPwd,"1");
                }else {
                    showToast("手机号或者密码不能为空!!!");
                }
                break;
        }
    }

    @Override
    public void dialogVisiable(boolean flag) {
        if (flag){
            dialog.setMessage("请稍后....");
            dialog.show();
        }else {
            dialog.dismiss();
        }
    }

    /**
     * 弹出Toast
     * @param content Toast内容
     */
    @Override
    public void showToast(String content) {
        Toast.makeText(LoginActivity.this, content, Toast.LENGTH_SHORT).show();
    }

    /**
     * 登陆返回结果
     * @param b true : 成功 fase :失败
     */
    @Override
    public void result(final boolean b,String data) {
        dialogVisiable(false);
        Thread thread = Thread.currentThread();
        System.out.println(thread);//主线程
        //是否要记录登陆账号和密码
        if (saveUser.isChecked()){
            PrefUtils.putString(this, Constant.USERNAME_AND_PASSWORD,mPhone+"&"+mPwd);
        }
        if (b){
            showToast("登陆成功"+data);
            PrefUtils.putBoolean(this,Constant.IS_LOGIN,true);
            startActivity();
        }else {
            showToast("登陆失败!!!"+data);
        }
    }

    private void startActivity() {
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }

    /**
     * 检验用户输入——界面相关逻辑处理
     *
     * @param username
     * @param password
     * @return
     */
    private boolean checkUserInfo(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            return false;
        }
        return true;
    }
}
