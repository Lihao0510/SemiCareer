package com.lihao.semicareer.fragment;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.lihao.semicareer.R;
import com.lihao.semicareer.activity.JobDetailActivity;
import com.lihao.semicareer.activity.JobSearchActivity;
import com.lihao.semicareer.contract.JobContract;
import com.lihao.semicareer.presenter.JobPresenterImpl;
import com.oridway.oridcore.eventmessage.ListEvent;
import com.oridway.oridcore.tools.BannerImageLoader;
import com.oridway.oridcore.utils.ListUtil;
import com.oridway.oridcore.utils.LogUtil;
import com.oridway.oridcore.widge.JobPtrHeader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by lihao on 2017/8/11.
 */

public class JobFragment extends BaseFragment implements JobContract.JobView, JobPtrHeader.PtrToolbarListener {

    @BindView(R.id.pfl_job)
    PtrFrameLayout pflLayout;
    @BindView(R.id.rv_job)
    RecyclerView jobRecyclerView;
    @BindView(R.id.ll_job_searchbar)
    LinearLayout searchBar;
    @BindView(R.id.ll_job_toolbar)
    LinearLayout toolbar;
    @BindView(R.id.itv_searchbar_icon)
    IconTextView searchBarIcon;
    @BindView(R.id.tv_searchbar_text)
    TextView searchBarText;

    private Banner mBanner;
    private List<String> bannerPicList;
    private List<String> bannerTitleList;
    private View bannerParent;


    private JobContract.JobPresenter mPresenter;

    private boolean toolbarVisible = true;
    private boolean canLoadMore = true;

    private AlphaAnimation hideAnim;
    private AlphaAnimation showAnim;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_job, container, false);
    }

    @Override
    protected void initFragment() {
        EventBus.getDefault().register(this);
        mPresenter = new JobPresenterImpl(this);
        initPullToRefresh();
        initClickListener();
        mPresenter.buildList(jobRecyclerView);
        initAnim();
        initBanner();
    }

    private void initBanner() {
        bannerPicList = new ArrayList<>();
        bannerTitleList = new ArrayList<>();
        bannerTitleList.add("我艹,又要剁手了");
        bannerTitleList.add("来看看有啥好东西");
        bannerTitleList.add("你该换床单了,吊丝");
        bannerPicList.add("https://img.alicdn.com/tfs/TB1LJQQSFXXXXboXXXXXXXXXXXX-520-280.jpg_q90");
        bannerPicList.add("https://img.alicdn.com/tfs/TB1w_QtSFXXXXanXFXXXXXXXXXX-520-280.jpg_q90");
        bannerPicList.add("https://img.alicdn.com/simba/img/TB1sAohSXXXXXahXFXXSutbFXXX.jpg");
        bannerParent = LayoutInflater.from(getActivityContext()).inflate(R.layout.item_job_banner, null);
        mBanner = (Banner) bannerParent.findViewById(R.id.banner_job_ad);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mBanner.setImageLoader(new BannerImageLoader());
        mBanner.setImages(bannerPicList);
        mBanner.setBannerAnimation(Transformer.Default);
        mBanner.setBannerTitles(bannerTitleList);
        mBanner.isAutoPlay(true);
        mBanner.setDelayTime(2400);
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mPresenter.setBanner(bannerParent);
        mBanner.start();
    }

    private void initAnim() {
        hideAnim = new AlphaAnimation(1.0f, 0f);
        showAnim = new AlphaAnimation(0f, 1.0f);
        hideAnim.setDuration(300);
        showAnim.setDuration(450);
        showAnim.setFillAfter(true);
        toolbar.setBackgroundColor(getResources().getColor(R.color.translucent));

    }

    private void initClickListener() {
        searchBar.setOnClickListener(this);
    }

    private void initPullToRefresh() {
        JobPtrHeader header = new JobPtrHeader(getContext(), this);
        header.setPadding(0, 80, 0, 45);
        pflLayout.setDurationToCloseHeader(1500);
        pflLayout.setHeaderView(header);
        pflLayout.addPtrUIHandler(header);
        header.initWithString("Finding...");

        pflLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPresenter.refreshData();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        final ArgbEvaluator evaluator = new ArgbEvaluator();
        final int[] bannerLocation = new int[2];
        final int[] itemLocation = new int[2];
        jobRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LogUtil.debugLog("是否滑动到底部:" + ListUtil.isSlideToBottom(recyclerView) + ";  canloadMore:" + canLoadMore);
                if (ListUtil.isSlideToBottom(recyclerView) && canLoadMore) {
                    mPresenter.loadMoreData();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                bannerParent.getLocationOnScreen(bannerLocation);
                int bannerY = bannerLocation[1];
                try {
                    recyclerView.getChildAt(1).getLocationOnScreen(itemLocation);
                } catch (NullPointerException e) {
                    itemLocation[1] = 500;
                }
                int itemY = itemLocation[1];
                //LogUtil.debugLog("BannerLocation:" + bannerY + "; ItemLocation:" + itemY);
                if (bannerY == 0 && itemY >= 400) {
                    toolbar.setBackgroundColor(Color.parseColor("#0000bfa5"));
                    searchBarIcon.setTextColor(Color.parseColor("#666666"));
                    searchBarText.setTextColor(Color.parseColor("#666666"));
                } else if (bannerY == 0 && itemY < 400) {
                    toolbar.setBackgroundColor(Color.parseColor("#FF00bfa5"));
                    searchBarIcon.setTextColor(Color.parseColor("#FFFFFF"));
                    searchBarText.setTextColor(Color.parseColor("#FFFFFF"));
                } else {
                    int toolbarColor = (int) evaluator.evaluate(-(bannerY / 540.0f), Color.parseColor("#0000bfa5"), Color.parseColor("#FF00bfa5"));
                    toolbar.setBackgroundColor(toolbarColor);
                    int textColor = (int) evaluator.evaluate(-(bannerY / 540.0f), Color.parseColor("#666666"), Color.parseColor("#FFFFFF"));
                    searchBarIcon.setTextColor(textColor);
                    searchBarText.setTextColor(textColor);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_job_searchbar:
                JobSearchActivity.startActivity(getContext());
                break;
        }
    }

    @Override
    public Context getActivityContext() {
        return getContext();
    }

    @Override
    public void onFinishRefresh() {
        pflLayout.refreshComplete();
    }

    @Override
    public void setCanLoadMore(boolean canLoadMore) {
        this.canLoadMore = canLoadMore;
    }

    @Override
    public void hideToolbar() {
        if (toolbarVisible) {
            toolbar.startAnimation(hideAnim);
            toolbar.setVisibility(View.GONE);
            toolbarVisible = false;
            toolbar.setBackgroundColor(Color.parseColor("#0000bfa5"));
        }
    }

    @Override
    public void showToolbar() {
        if (!toolbarVisible) {
            toolbar.startAnimation(showAnim);
            toolbar.setVisibility(View.VISIBLE);
            toolbarVisible = true;
            toolbar.setBackgroundColor(Color.parseColor("#0000bfa5"));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loadMoreData(ListEvent event) {
        /*if (event.eventType == ListEvent.HOME_JOB_LIST_LOADMORE) {
            LogUtil.debugLog("收到加载更多信息!");
            mPresenter.loadMoreData();
        }*/
    }

    @Override
    public void onResume() {
        super.onResume();
        setCanLoadMore(true);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void goJobDetail(ListEvent event) {
        if (event.eventType == ListEvent.HOME_JOB_LIST_CLICK) {
            LogUtil.debugLog("jobID:" + event.eventBody);
            JobDetailActivity.startActivity(getContext(), (int) event.eventBody);
        }
    }
}
