package com.lihao.semicareer.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.jaeger.library.StatusBarUtil;
import com.lihao.semicareer.R;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by lihao on 2017/8/15.
 */

public abstract class SwipeBackBaseActivity extends SwipeBackActivity implements View.OnClickListener {

    protected Context activityContext;
    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutRes());
        activityContext = this;
        ButterKnife.bind(this);
        setSwipeBackEnable(true);
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        if (changeStatusbarColor()) {
            StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.colorPrimary));
        } else {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(getResources().getColor(R.color.translucent));
            }
        }
        initActivity();
    }

    protected abstract int setLayoutRes();

    protected abstract void initActivity();

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

    protected abstract boolean changeStatusbarColor();

}
