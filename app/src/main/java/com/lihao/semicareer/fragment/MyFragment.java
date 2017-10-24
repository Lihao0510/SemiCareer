package com.lihao.semicareer.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.lihao.semicareer.R;
import com.lihao.semicareer.activity.CollectActivity;
import com.lihao.semicareer.activity.LoginActivity;
import com.lihao.semicareer.activity.MessageActivity;
import com.lihao.semicareer.activity.MyMessageActivity;
import com.lihao.semicareer.activity.ReflectActivity;
import com.lihao.semicareer.activity.ResumeActivity;
import com.lihao.semicareer.activity.SendHistoryActivity;
import com.lihao.semicareer.activity.SettingActivity;
import com.lihao.semicareer.application.CoreApplication;
import com.lihao.semicareer.contract.MyContract;
import com.lihao.semicareer.entity.LoginMessage;
import com.lihao.semicareer.presenter.MyPresenterImpl;
import com.oridway.oridcore.tools.GlideApp;
import com.oridway.oridcore.utils.ToastUtil;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * Created by lihao on 2017/8/11.
 */

public class MyFragment extends BaseFragment implements MyContract.MyView {


    @BindView(R.id.sv_center_container)
    ScrollView centerContainer;
    @BindView(R.id.civ_center_head)
    CircleImageView headView;
    @BindView(R.id.tv_center_name)
    TextView userName;
    @BindView(R.id.tv_center_career)
    TextView userCareer;
    @BindView(R.id.tv_center_look_count)
    TextView lookCount;
    @BindView(R.id.tv_center_interview_count)
    TextView interviewCount;
    @BindView(R.id.tv_center_message_count)
    TextView messageCount;
    @BindView(R.id.ll_center_mymessage)
    LinearLayout myMessage;
    @BindView(R.id.ll_center_resume)
    LinearLayout myResume;
    @BindView(R.id.ll_center_collect)
    LinearLayout myCollect;
    @BindView(R.id.ll_center_private)
    LinearLayout myPrivate;
    @BindView(R.id.ll_center_reflect)
    LinearLayout myReflect;
    @BindView(R.id.ll_center_setting)
    LinearLayout mySettiing;
    @BindView(R.id.ll_center_look)
    LinearLayout messageLook;
    @BindView(R.id.ll_center_invite)
    LinearLayout messageInvite;
    @BindView(R.id.ll_center_mail)
    LinearLayout messageMail;

    private MyContract.MyPresenter mPresenter;
    private LoginMessage loginMessage;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    protected void initFragment() {
        mPresenter = new MyPresenterImpl(this);
        initClickListener();
        OverScrollDecoratorHelper.setUpOverScroll(centerContainer);
        mPresenter.getMessageCount();
    }

    private void initShowMessage() {
        if (CoreApplication.loginStatus) {
            loginMessage = CoreApplication.appLoginMessage;
            GlideApp.with(getActivity())
                    .load(loginMessage.userHead)
                    .centerCrop()
                    .into(headView);
            userName.setText(loginMessage.userCName);
            userCareer.setText(loginMessage.userSign);
            userName.setOnClickListener(null);
            userCareer.setOnClickListener(null);
            headView.setOnClickListener(null);
        } else {
            GlideApp.with(getActivity())
                    .load(R.drawable.icon_head_boy)
                    .centerCrop()
                    .into(headView);
            userName.setText("您尚未登录");
            userCareer.setText("点击登录,获得更好的求职体验");
            userName.setOnClickListener(this);
            userCareer.setOnClickListener(this);
            headView.setOnClickListener(this);
        }

    }

    private void initClickListener() {
        myMessage.setOnClickListener(this);
        mySettiing.setOnClickListener(this);
        myCollect.setOnClickListener(this);
        myPrivate.setOnClickListener(this);
        myReflect.setOnClickListener(this);
        myResume.setOnClickListener(this);
        messageInvite.setOnClickListener(this);
        messageLook.setOnClickListener(this);
        messageMail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_center_resume:
                if (CoreApplication.loginStatus) {
                    ResumeActivity.startActivity(getContext());
                } else {
                    LoginActivity.startActivity(getActivityContext());
                }
                break;
            case R.id.tv_center_name:
                LoginActivity.startActivity(getActivityContext());
                break;
            case R.id.civ_center_head:
                LoginActivity.startActivity(getActivityContext());
                break;
            case R.id.tv_center_career:
                LoginActivity.startActivity(getActivityContext());
                break;
            case R.id.ll_center_setting:
                SettingActivity.startActivity(getActivityContext());
                break;
            case R.id.ll_center_reflect:
                ReflectActivity.startActivity(getActivityContext());
                break;
            case R.id.ll_center_collect:
                if (CoreApplication.loginStatus) {
                    CollectActivity.startActivity(getActivityContext());
                } else {
                    LoginActivity.startActivity(getActivityContext());
                }
                break;
            case R.id.ll_center_look:
                MessageActivity.startActivity(getActivityContext(), MessageActivity.MSG_LOOK);
                break;
            case R.id.ll_center_invite:
                MessageActivity.startActivity(getActivityContext(), MessageActivity.MSG_INVITE);
                break;
            case R.id.ll_center_mail:
                MessageActivity.startActivity(getActivityContext(), MessageActivity.MSG_MESSAGE);
                break;
            case R.id.ll_center_private:
                if (CoreApplication.loginStatus) {
                    SendHistoryActivity.startActivity(getActivityContext());
                } else {
                    LoginActivity.startActivity(getActivityContext());
                }
                break;
            case R.id.ll_center_mymessage:
                if (CoreApplication.loginStatus) {
                    MyMessageActivity.startActivity(getActivityContext());
                } else {
                    LoginActivity.startActivity(getActivityContext());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public Context getActivityContext() {
        return getContext();
    }

    @Override
    public void updateMessageStatus(int lookNum, int interviewNum, int messageNum) {
        lookCount.setText("" + lookNum);
        interviewCount.setText("" + interviewNum);
        messageCount.setText("" + messageNum);
    }

    @Override
    public void onResume() {
        super.onResume();
        initShowMessage();
    }
}
