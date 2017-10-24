package com.lihao.semicareer.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lihao.semicareer.R;
import com.lihao.semicareer.application.CoreApplication;
import com.lihao.semicareer.entity.LoginMessage;
import com.lihao.semicareer.model.LoginModel;
import com.maning.mndialoglibrary.MProgressDialog;
import com.maning.mndialoglibrary.MStatusDialog;
import com.oridway.oridcore.network.ResponseObject;
import com.oridway.oridcore.utils.LogUtil;
import com.oridway.oridcore.utils.PrefUtil;
import com.oridway.oridcore.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends SwipeBackBaseActivity {

    private static final String PREF_NAME = "appsys_prefs";
    public static final String USER_PHONE_KEY = "user_phone";
    public static final String USER_PWD_KEY = "user_pwd";


    @BindView(R.id.et_login_phone)
    EditText userPhoneText;
    @BindView(R.id.et_login_pwd)
    EditText userPwdText;
    @BindView(R.id.bt_login_action)
    Button loginAction;

    private LoginModel loginModel;
    private MStatusDialog mStatusDialog;
    private MProgressDialog mProgressDialog;

    private String userPhone;
    private String userPwd;


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initActivity() {
        loginModel = new LoginModel();
        initView();
        initClick();
    }

    private void initView() {
        userPhone = PrefUtil.getStringProperty(this, PREF_NAME, USER_PHONE_KEY);
        if (userPhone != null && userPhone.length() > 10) {
            userPhoneText.setText(userPhone);
        }
    }

    private void initClick() {
        loginAction.setOnClickListener(this);
    }

    @Override
    protected boolean changeStatusbarColor() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login_action:
                doLogin();
                break;
        }
    }

    private void doLogin() {
        userPhone = userPhoneText.getText().toString().trim();
        userPwd = userPwdText.getText().toString().trim();

        if (userPhone.length() < 11 || userPwd.length() < 6) {
            ToastUtil.showToast("输入信息有误!");
            return;
        }

        showProgressDialog("正在登录中");

        Map<String, Object> params = new HashMap<>();
        params.put("userPhone", userPhone);
        params.put("userPwd", userPwd);

        loginModel.loginByPhone(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseObject<LoginMessage>>() {
                    @Override
                    public void onCompleted() {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onNext(ResponseObject<LoginMessage> loginMessageResponseObject) {
                        switch (loginMessageResponseObject.status) {
                            case 0:
                                showSendFailDialog("密码错误");
                                break;
                            case 4:
                                showSendFailDialog("登录失败");
                                break;
                            case 1:
                                CoreApplication.loginStatus = true;
                                showSendSuccessDialog("登陆成功");
                                CoreApplication.appLoginMessage = loginMessageResponseObject.getResult();
                                saveLoginMessage();
                                loginAction.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        finish();
                                    }
                                }, 2200);
                                break;
                        }
                    }
                });
    }

    private void saveLoginMessage() {
        PrefUtil.setStringProperty(this, PREF_NAME, USER_PHONE_KEY, userPhone);
        PrefUtil.setStringProperty(this, PREF_NAME, USER_PWD_KEY, userPwd);
    }

    private void showSendSuccessDialog(String msg) {
        mStatusDialog = new MStatusDialog(this);
        mStatusDialog.showSuccess(msg);
    }

    private void showProgressDialog(String msg) {
        mProgressDialog = new MProgressDialog(this);
        mProgressDialog.show(msg);
    }

    private void showNotLoginDialog() {
        mStatusDialog = new MStatusDialog(this);
        mStatusDialog.showError("您尚未登录");
    }

    private void showSendFailDialog(String msg) {
        mStatusDialog = new MStatusDialog(this);
        mStatusDialog.showError(msg);
    }
}
