package com.lihao.semicareer.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lihao.semicareer.R;
import com.lihao.semicareer.application.CoreApplication;
import com.lihao.semicareer.entity.LoginMessage;
import com.lihao.semicareer.model.LoginModel;
import com.oridway.oridcore.eventmessage.GlobalEvent;
import com.oridway.oridcore.network.ResponseObject;
import com.oridway.oridcore.utils.LogUtil;
import com.oridway.oridcore.utils.PrefUtil;
import com.oridway.oridcore.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WelcomeActivity extends BaseActivity {

    private static final String PREF_NAME = "appsys_prefs";
    private boolean isFirstLaunch = false;
    private LoginModel loginModel;

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initActivity() {
        LogUtil.debugLog("welcomeActivityShow");
        initWelcomeEvents();
        EventBus.getDefault().register(this);
        EventBus.getDefault().post(GlobalEvent.newEvent(GlobalEvent.WELCOME_PAGE_LOAD_COMPLETED));
    }

    @Override
    protected boolean changeStatusbarColor() {
        return true;
    }

    private void initWelcomeEvents() {
        int launchTimes = PrefUtil.getIntProperty(getApplicationContext(), PREF_NAME, "LaunchTimes");
        if (launchTimes == 0) {
            LogUtil.debugLog("这是第一次登录,需要加载引导页!");
            isFirstLaunch = true;
        } else {
            LogUtil.debugLog("这不是第一次登录,直接进入主页!");
            isFirstLaunch = false;
        }
        PrefUtil.setIntProperty(getApplicationContext(), PREF_NAME, "LaunchTimes", 1 + launchTimes);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void goMainActivity(GlobalEvent event) {
        if (event.eventType == GlobalEvent.EXTERNAL_COMPONENTS_LOAD_COMPLETED) {
            tryAutoLogin();
            LogUtil.debugLog("goMainActivity");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isFirstLaunch) {
                        LogUtil.debugLog("前往引导页!");
                    } else {
                        LogUtil.debugLog("前往主页!");
                    }
                    MainActivity.startActivity(WelcomeActivity.this);
                    finish();
                }
            }, 2000);
        }
    }

    private void tryAutoLogin() {
        LogUtil.debugLog("执行自动登录");
        String userPhone = PrefUtil.getStringProperty(getApplicationContext(), PREF_NAME, LoginActivity.USER_PHONE_KEY);
        String userPwd = PrefUtil.getStringProperty(getApplicationContext(), PREF_NAME, LoginActivity.USER_PWD_KEY);
        if (userPhone != null && userPwd != null && userPhone.length() > 10) {
            loginModel = new LoginModel();
            Map<String, Object> params = new HashMap<>();
            params.put("userPhone", userPhone);
            params.put("userPwd", userPwd);
            loginModel.loginByPhone(params)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ResponseObject<LoginMessage>>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            ToastUtil.showToast("登陆失败!");
                        }

                        @Override
                        public void onNext(ResponseObject<LoginMessage> loginMessageResponseObject) {
                            switch (loginMessageResponseObject.status) {
                                case 0:
                                    ToastUtil.showToast("登陆失败!");
                                    break;
                                case 4:
                                    ToastUtil.showToast("登陆失败!");
                                    break;
                                case 1:
                                    CoreApplication.loginStatus = true;
                                    CoreApplication.appLoginMessage = loginMessageResponseObject.getResult();
                                    break;
                            }
                        }
                    });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
