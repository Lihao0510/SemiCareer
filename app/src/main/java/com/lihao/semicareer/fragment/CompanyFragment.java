package com.lihao.semicareer.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joanzapata.iconify.widget.IconTextView;
import com.lihao.semicareer.R;
import com.lihao.semicareer.activity.CompanyDetailActivity;
import com.lihao.semicareer.activity.CompanySearchActivity;
import com.lihao.semicareer.activity.JobDetailActivity;
import com.lihao.semicareer.contract.CompanyContract;
import com.lihao.semicareer.presenter.CompanyPresenterImpl;
import com.oridway.oridcore.eventmessage.ListEvent;
import com.oridway.oridcore.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * Created by lihao on 2017/8/11.
 */

public class CompanyFragment extends BaseFragment implements CompanyContract.CompanyView {

    private CompanyContract.CompanyPresenter mPresenter;

    @BindView(R.id.rv_company)
    RecyclerView companyListView;
    @BindView(R.id.itv_company_search)
    IconTextView companySearch;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_company, container, false);
    }

    @Override
    protected void initFragment() {
        mPresenter = new CompanyPresenterImpl(this);
        EventBus.getDefault().register(this);
        initClickListener();
        buildCompanyList();
    }

    private void buildCompanyList() {
        mPresenter.buildList(companyListView);
    }

    private void initClickListener() {
        companySearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.itv_company_search:
                CompanySearchActivity.startActivity(getContext());
                break;
        }
    }

    @Override
    public Context getActivityContext() {
        return getContext();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void goCompanyDetail(ListEvent event) {
        if (event.eventType == ListEvent.HOME_COMPANY_LIST_CLICK) {
            LogUtil.debugLog("CompanyID:" + event.eventBody);
            CompanyDetailActivity.startActivity(getContext(), (int) event.eventBody);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
