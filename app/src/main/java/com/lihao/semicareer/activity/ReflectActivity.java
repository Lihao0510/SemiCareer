package com.lihao.semicareer.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.lihao.semicareer.R;
import com.lihao.semicareer.application.CoreApplication;
import com.maning.mndialoglibrary.MStatusDialog;

import butterknife.BindView;

public class ReflectActivity extends SwipeBackBaseActivity {

    @BindView(R.id.itv_toolbar_back)
    IconTextView backIcon;
    @BindView(R.id.itv_toolbar_right)
    IconTextView rightIcon;
    @BindView(R.id.tv_toolbar_title)
    TextView titleText;
    @BindView(R.id.bt_login_action)
    Button loginAction;
    @BindView(R.id.et_reflect_title)
    EditText titleEditText;
    @BindView(R.id.et_reflect_content)
    EditText contentEditText;

    private MStatusDialog mStatusDialog;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ReflectActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_reflect;
    }

    @Override
    protected void initActivity() {
        initView();
        initClick();
    }

    private void initView() {
        titleText.setText("问题反馈");
        loginAction.setText("提交");
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
                submitReflect();
                break;
        }
    }

    private void submitReflect() {
        String title = titleEditText.getText().toString().trim();
        String content = contentEditText.getText().toString().trim();
        if (title.length() <= 1 || content.length() <= 1) {
            showErrorDialog("输入不合法");
            return;
        }
        showSendSuccessDialog("提交成功");
        loginAction.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2200);
    }

    private void showSendSuccessDialog(String msg) {
        mStatusDialog = new MStatusDialog(this);
        mStatusDialog.showSuccess(msg);
    }

    private void showErrorDialog(String msg) {
        mStatusDialog = new MStatusDialog(this);
        mStatusDialog.showError(msg);
    }
}
