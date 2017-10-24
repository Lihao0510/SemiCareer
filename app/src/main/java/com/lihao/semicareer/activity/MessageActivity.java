package com.lihao.semicareer.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.lihao.semicareer.R;

import butterknife.BindView;

public class MessageActivity extends SwipeBackBaseActivity {

    public static final int MSG_LOOK = 0;
    public static final int MSG_INVITE = 1;
    public static final int MSG_MESSAGE = 2;

    @BindView(R.id.itv_toolbar_back)
    IconTextView backIcon;
    @BindView(R.id.tv_toolbar_title)
    TextView titleText;
    @BindView(R.id.tv_empty_text)
    TextView emptyMessage;


    private int msgType;

    public static void startActivity(Context context, int msgType) {
        Intent intent = new Intent(context, MessageActivity.class);
        intent.putExtra("msgType", msgType);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_message;
    }

    @Override
    protected void initActivity() {
        initData();
        initView();
        initClick();
    }

    private void initData() {
        msgType = getIntent().getIntExtra("msgType", MSG_LOOK);
    }

    private void initView() {
        switch (msgType){
            case MSG_LOOK:
                titleText.setText("简历查看情况");
                emptyMessage.setText("简历还没被查看过");
                break;
            case MSG_INVITE:
                titleText.setText("面试邀请");
                emptyMessage.setText("没有面试邀请,请再接再厉");
                break;
            case MSG_MESSAGE:
                titleText.setText("站内信");
                emptyMessage.setText("还没有站内信");
                break;
        }
    }

    private void initClick() {
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
        }
    }
}
