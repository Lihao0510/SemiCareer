package com.lihao.semicareer.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.lihao.semicareer.R;

import butterknife.BindView;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class ResumeActivity extends SwipeBackBaseActivity {

    @BindView(R.id.itv_toolbar_back)
    IconTextView backIcon;
    @BindView(R.id.itv_toolbar_right)
    IconTextView rightIcon;
    @BindView(R.id.tv_toolbar_title)
    TextView titleText;
    @BindView(R.id.sv_resume_container)
    ScrollView container;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ResumeActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int setLayoutRes() {
        return R.layout.activity_resume;
    }

    @Override
    protected void initActivity() {
        initView();
        initClick();
    }

    private void initView() {
        titleText.setText("我的简历");
        OverScrollDecoratorHelper.setUpOverScroll(container);
    }

    private void initClick() {
        backIcon.setOnClickListener(this);
        rightIcon.setOnClickListener(this);
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
        }
    }
}
