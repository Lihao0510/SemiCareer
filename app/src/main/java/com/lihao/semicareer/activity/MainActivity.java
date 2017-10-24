package com.lihao.semicareer.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.lihao.semicareer.R;
import com.lihao.semicareer.application.CoreApplication;
import com.lihao.semicareer.fragment.CompanyFragment;
import com.lihao.semicareer.fragment.JobFragment;
import com.lihao.semicareer.fragment.MyFragment;
import com.lihao.semicareer.fragment.NewsFragment;
import com.maning.mndialoglibrary.MStatusDialog;
import com.oridway.oridcore.utils.LogUtil;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    public static final String TAB_JOB = "TAB_JOB";
    public static final String TAB_COMPANY = "TAB_COMPANY";
    public static final String TAB_NEWS = "TAB_NEWS";
    public static final String TAB_MY = "TAB_MY";

    @BindView(R.id.ft_tabhost)
    FragmentTabHost mTabHost;

    private TabWidget mTabWidget;
    private MStatusDialog mStatusDialog;


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initActivity() {
        initFragmentHost();
        initLoginMessage();
    }

    private void initFragmentHost() {
        mTabHost.setup(activityContext, getSupportFragmentManager(), R.id.fl_fragment_container);
        mTabHost.addTab(mTabHost.newTabSpec(TAB_JOB).setIndicator(getTabItemView("职位", R.drawable.selector_tab_job)), JobFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(TAB_COMPANY).setIndicator(getTabItemView("名企", R.drawable.selector_tab_company)), CompanyFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(TAB_NEWS).setIndicator(getTabItemView("新闻", R.drawable.selector_tab_news)), NewsFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(TAB_MY).setIndicator(getTabItemView("我的", R.drawable.selector_tab_my)), MyFragment.class, null);

        mTabWidget = mTabHost.getTabWidget();
        mTabWidget.setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabWidget.setBackgroundResource(R.color.pureWhite);
        mTabWidget.setPadding(0, 15, 0, 15);
    }

    private void initLoginMessage() {
        if (CoreApplication.loginStatus){
            showSendSuccessDialog("登陆成功!");
        }
    }

    private View getTabItemView(String tabTitle, int drawableResId) {
        View tabView = getLayoutInflater().inflate(R.layout.item_tab_indicator, null, false);
        ((ImageView) tabView.findViewById(R.id.iv_indicator_image)).setImageResource(drawableResId);
        ((TextView) tabView.findViewById(R.id.tv_indicator_text)).setText(tabTitle);
        return tabView;
    }

    @Override
    protected boolean changeStatusbarColor() {
        return false;
    }

    private void showSendSuccessDialog(String msg) {
        mStatusDialog = new MStatusDialog(this);
        mStatusDialog.showSuccess(msg);
    }
}
