package com.lihao.semicareer.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.lihao.semicareer.R;
import com.lihao.semicareer.application.CoreApplication;
import com.lihao.semicareer.entity.LoginMessage;
import com.lihao.semicareer.model.ResumeModel;
import com.maning.mndialoglibrary.MStatusDialog;
import com.oridway.oridcore.tools.GlideApp;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class MyMessageActivity extends SwipeBackBaseActivity {

    @BindView(R.id.itv_toolbar_back)
    IconTextView backIcon;
    @BindView(R.id.itv_toolbar_right)
    IconTextView rightIcon;
    @BindView(R.id.tv_toolbar_title)
    TextView titleText;
    @BindView(R.id.bt_login_action)
    Button loginAction;
    @BindView(R.id.sv_mymessage_container)
    ScrollView container;
    @BindView(R.id.civ_center_head)
    CircleImageView userHead;
    @BindView(R.id.tv_user_cname)
    TextView userCName;
    @BindView(R.id.tv_user_name)
    TextView userName;
    @BindView(R.id.et_user_id)
    EditText userID;
    @BindView(R.id.et_user_phone)
    EditText userPhone;
    @BindView(R.id.et_user_mail)
    EditText userMail;
    @BindView(R.id.et_user_time)
    EditText userCreateTime;

    private MStatusDialog mStatusDialog;
    private LoginMessage mLoginMessage;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MyMessageActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_my_message;
    }

    @Override
    protected void initActivity() {
        initView();
        initClick();
        getMyMessage();
    }

    private void getMyMessage() {
        if (!CoreApplication.loginStatus) {
            return;
        }

        mLoginMessage = CoreApplication.appLoginMessage;

        if (mLoginMessage == null) {
            return;
        }

        userCName.setText(mLoginMessage.userCName);
        userName.setText("用户名: " + mLoginMessage.userName);
        userID.setText("" + mLoginMessage.userID);
        userPhone.setText(mLoginMessage.userPhone);
        userMail.setText(mLoginMessage.userEmail);
        userCreateTime.setText(mLoginMessage.createTime.substring(0, 10));

        GlideApp.with(this).load(mLoginMessage.userHead).into(userHead);
    }

    private void initView() {
        titleText.setText("我的资料");
        rightIcon.setVisibility(View.VISIBLE);
        rightIcon.setText("{icon-note}");
        OverScrollDecoratorHelper.setUpOverScroll(container);
    }

    private void initClick() {
        backIcon.setOnClickListener(this);
        rightIcon.setOnClickListener(this);
        loginAction.setOnClickListener(this);
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
                enableEdit();
                break;
            case R.id.bt_login_action:
                handleLoginAction();
                break;
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

    private void enableEdit() {
        showErrorDialog("请登录网页端编辑");
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkLoginStatus();
    }

    private void checkLoginStatus() {
        if (CoreApplication.loginStatus) {
            loginAction.setText("退出登录");
        } else {
            loginAction.setText("登录");
        }
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
