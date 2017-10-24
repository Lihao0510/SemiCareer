package com.lihao.semicareer.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.lihao.semicareer.R;
import com.lihao.semicareer.application.CoreApplication;
import com.maning.mndialoglibrary.MProgressDialog;
import com.maning.mndialoglibrary.MStatusDialog;

import butterknife.BindInt;
import butterknife.BindView;

public class SettingActivity extends SwipeBackBaseActivity {

    @BindView(R.id.itv_toolbar_back)
    IconTextView backIcon;
    @BindView(R.id.itv_toolbar_right)
    IconTextView rightIcon;
    @BindView(R.id.tv_toolbar_title)
    TextView titleText;
    @BindView(R.id.bt_login_action)
    Button loginAction;

    private MStatusDialog mStatusDialog;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initActivity() {
        initView();
        initClick();
    }

    private void initView() {
        titleText.setText("系统设置");
    }

    private void initClick() {
        loginAction.setOnClickListener(this);
        backIcon.setOnClickListener(this);
    }

    @Override
    protected boolean changeStatusbarColor() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.itv_toolbar_back:
                finish();
                break;
            case R.id.itv_toolbar_right:
                break;
            case R.id.bt_login_action:
                handleLoginAction();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkLoginStatus();
    }

    private void checkLoginStatus(){
        if (CoreApplication.loginStatus) {
            loginAction.setText("退出登录");
        } else {
            loginAction.setText("登录");
        }
    }

    private void handleLoginAction() {
        if (CoreApplication.loginStatus) {
            CoreApplication.loginStatus = false;
            showSendSuccessDialog("已退出登录");
        } else {
            LoginActivity.startActivity(this);
        }
        checkLoginStatus();
    }

    private void showSendSuccessDialog(String msg) {
        mStatusDialog = new MStatusDialog(this);
        mStatusDialog.showSuccess(msg);
    }
}
