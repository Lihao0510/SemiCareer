package com.lihao.semicareer.presenter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lihao.semicareer.adapter.MainJobAdapter;
import com.lihao.semicareer.contract.JobContract;
import com.lihao.semicareer.entity.CareerJob;
import com.lihao.semicareer.model.JobModel;
import com.oridway.oridcore.network.ResponseObject;
import com.oridway.oridcore.utils.LogUtil;
import com.oridway.oridcore.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lihao on 2017/8/13.
 */

public class JobPresenterImpl implements JobContract.JobPresenter {

    private JobContract.JobView mView;
    private List<CareerJob> jobItemList;
    private LinearLayoutManager layoutManager;
    private MainJobAdapter mainJobAdapter;
    private JobModel mModel;

    private int curJobPage = 0;

    public JobPresenterImpl(JobContract.JobView view) {
        this.mView = view;
        init();
    }

    @Override
    public void init() {
        mModel = new JobModel();
        layoutManager = new LinearLayoutManager(mView.getActivityContext(), LinearLayoutManager.VERTICAL, false);
        jobItemList = new ArrayList<>();
    }

    @Override
    public void buildList(RecyclerView recyclerView) {
        initJobList();
        mainJobAdapter = new MainJobAdapter(jobItemList);
        recyclerView.setAdapter(mainJobAdapter);
        recyclerView.setLayoutManager(layoutManager);
        OverScrollDecoratorHelper.setUpOverScroll(recyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
        mainJobAdapter.notifyDataSetChanged();
    }

    @Override
    public void setBanner(View view) {
        mainJobAdapter.addHeaderView(view);
    }

    @Override
    public void loadMoreData() {
        curJobPage++;
        mModel.queryJobList(curJobPage, JobModel.DEFALT_JOB_PAGESIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseObject<List<CareerJob>>>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.debugLog("获取Job信息完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.debugLog("获取Job失败:" + e.toString());
                    }

                    @Override
                    public void onNext(ResponseObject<List<CareerJob>> listResponseObject) {
                        switch (listResponseObject.status) {
                            case 0:
                                ToastUtil.showToast("没有更多数据!");
                                mView.setCanLoadMore(false);
                                curJobPage--;
                                break;
                            case 4:

                                break;
                            case 1:
                                jobItemList.addAll(listResponseObject.getResult());
                                mainJobAdapter.notifyDataSetChanged();
                                break;
                        }
                    }
                });

    }

    @Override
    public void refreshData() {
        mView.setCanLoadMore(true);
        initJobList();
    }


    private void initJobList() {
        curJobPage = 0;
        jobItemList.clear();
        mModel.queryJobList(0, JobModel.DEFALT_JOB_PAGESIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseObject<List<CareerJob>>>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.debugLog("获取Job信息完成");
                        mView.onFinishRefresh();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.debugLog("获取Job失败:" + e.toString());
                        mView.onFinishRefresh();
                    }

                    @Override
                    public void onNext(ResponseObject<List<CareerJob>> listResponseObject) {
                        switch (listResponseObject.status) {
                            case 0:
                                LogUtil.debugLog("没有更多数据!");
                                mView.setCanLoadMore(false);
                                break;
                            case 4:

                                break;
                            case 1:
                                jobItemList.addAll(listResponseObject.getResult());
                                mainJobAdapter.notifyDataSetChanged();
                                break;
                        }
                    }
                });

    }
}
