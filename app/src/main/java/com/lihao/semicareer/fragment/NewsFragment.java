package com.lihao.semicareer.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lihao.semicareer.R;
import com.lihao.semicareer.contract.JobContract;
import com.oridway.oridcore.widge.JobPtrHeader;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by lihao on 2017/8/11.
 */

public class NewsFragment extends BaseFragment implements JobContract.JobView, JobPtrHeader.PtrToolbarListener {

    @BindView(R.id.pfl_job)
    PtrFrameLayout ptrLayout;
    @BindView(R.id.rv_news)
    RecyclerView newsListView;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    protected void initFragment() {
        initPtrLayout();
    }

    private void initPtrLayout() {
        JobPtrHeader header = new JobPtrHeader(getContext(), this);
        header.setPadding(0, 80, 0, 45);
        ptrLayout.setDurationToCloseHeader(1500);
        ptrLayout.setHeaderView(header);
        ptrLayout.addPtrUIHandler(header);
        header.initWithString("LATEST NEWS");

        ptrLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void hideToolbar() {

    }

    @Override
    public void showToolbar() {

    }

    @Override
    public Context getActivityContext() {
        return getContext();
    }

    @Override
    public void onFinishRefresh() {

    }
}
