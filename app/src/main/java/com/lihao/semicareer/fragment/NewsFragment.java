package com.lihao.semicareer.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lihao.semicareer.R;
import com.lihao.semicareer.contract.JobContract;
import com.lihao.semicareer.contract.NewsContract;
import com.lihao.semicareer.presenter.NewsPresenterImpl;
import com.oridway.oridcore.eventmessage.ListEvent;
import com.oridway.oridcore.utils.LogUtil;
import com.oridway.oridcore.widge.JobPtrHeader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by lihao on 2017/8/11.
 */

public class NewsFragment extends BaseFragment implements NewsContract.NewsView, JobPtrHeader.PtrToolbarListener {

    @BindView(R.id.pfl_job)
    PtrFrameLayout ptrLayout;
    @BindView(R.id.rv_news)
    RecyclerView newsListView;

    private NewsContract.NewsPresenter mPresenter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    protected void initFragment() {
        mPresenter = new NewsPresenterImpl(this);
        EventBus.getDefault().register(this);
        initPtrLayout();
        mPresenter.buildList(newsListView);
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
                mPresenter.onRefresh();

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
    public Context getActivityContext() {
        return getContext();
    }

    @Override
    public void onRefreshFinished() {
        ptrLayout.refreshComplete();
    }


    @Override
    public void hideToolbar() {

    }

    @Override
    public void showToolbar() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loadMoreData(ListEvent event) {
        if (event.eventType == ListEvent.NEWS_LIST_LOADMORE) {
            LogUtil.debugLog("收到加载更多信息!");
            mPresenter.onLoadMore();
        }
    }
}
