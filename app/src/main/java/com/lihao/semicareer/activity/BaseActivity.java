package com.lihao.semicareer.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.jaeger.library.StatusBarUtil;
import com.lihao.semicareer.R;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;

/**
 * Created by lihao on 2017/8/10.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Context activityContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutRes());
        activityContext = this;
        if (changeStatusbarColor()) {
            StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.colorPrimary));
        } else {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(getResources().getColor(R.color.translucent));
            }
        }
        ButterKnife.bind(this);
        initActivity();
    }

    protected abstract int setLayoutRes();

    protected abstract void initActivity();

    protected abstract boolean changeStatusbarColor();

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
