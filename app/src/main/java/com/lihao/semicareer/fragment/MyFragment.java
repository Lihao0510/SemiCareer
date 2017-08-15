package com.lihao.semicareer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.lihao.semicareer.R;
import com.oridway.oridcore.tools.GlideApp;
import com.oridway.oridcore.utils.ToastUtil;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * Created by lihao on 2017/8/11.
 */

public class MyFragment extends BaseFragment {


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
    LinearLayout mymessageButton;
    @BindView(R.id.ll_center_backlog)
    LinearLayout backlogButton;
    @BindView(R.id.ll_center_myproject)
    LinearLayout myprojectButton;
    @BindView(R.id.ll_center_myapply)
    LinearLayout myapplyButton;
    @BindView(R.id.ll_center_clockin)
    LinearLayout clockinButton;
    @BindView(R.id.ll_center_travel)
    LinearLayout travelButton;
    @BindView(R.id.ll_center_diary)
    LinearLayout textButton;
    @BindView(R.id.ll_center_notice)
    LinearLayout noticeButton;
    @BindView(R.id.ll_center_mydepartment)
    LinearLayout departmentButton;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    protected void initFragment() {
        initShowMessage();
        initClickListener();
        OverScrollDecoratorHelper.setUpOverScroll(centerContainer);
    }

    private void initShowMessage() {
        GlideApp.with(getActivity())
                .load("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2153970753,602173372&fm=117&gp=0.jpg")
                .centerCrop()
                .into(headView);

    }

    private void initClickListener() {
        backlogButton.setOnClickListener(this);
        myprojectButton.setOnClickListener(this);
        myapplyButton.setOnClickListener(this);
        clockinButton.setOnClickListener(this);
        travelButton.setOnClickListener(this);
        textButton.setOnClickListener(this);
        noticeButton.setOnClickListener(this);
        departmentButton.setOnClickListener(this);
        mymessageButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ToastUtil.showToast("" + v.getId());
        switch (v.getId()) {
            case R.id.ll_center_backlog:

                break;
            case R.id.ll_center_myproject:

                break;
            case R.id.ll_center_myapply:

                break;
            case R.id.ll_center_clockin:

                break;
            case R.id.ll_center_diary:

                break;
            case R.id.ll_center_travel:

                break;
            case R.id.ll_center_mydepartment:

                break;
            case R.id.ll_center_notice:

                break;
            case R.id.ll_center_mymessage:

                break;
            default:
                break;
        }
    }
}
