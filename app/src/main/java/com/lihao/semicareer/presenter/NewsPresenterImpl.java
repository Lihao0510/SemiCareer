package com.lihao.semicareer.presenter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lihao.semicareer.adapter.NewsAdapter;
import com.lihao.semicareer.contract.NewsContract;
import com.lihao.semicareer.entity.CareerNews;
import com.lihao.semicareer.model.JobModel;
import com.lihao.semicareer.model.NewsModel;
import com.oridway.oridcore.network.ResponseObject;
import com.oridway.oridcore.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lihao on 2017/8/17.
 */

public class NewsPresenterImpl implements NewsContract.NewsPresenter {

    private NewsContract.NewsView mView;
    private LinearLayoutManager layoutManager;
    private List<CareerNews> newsList;
    private NewsAdapter newsAdapter;
    private NewsModel newsModel;

    private int curPageNum = 0;
    private int newsTag = -1;
    private int newsType = -1;
    private String newsAuthor = "";

    public NewsPresenterImpl(NewsContract.NewsView view) {
        this.mView = view;
        init();
    }

    @Override
    public void init() {
        newsModel = new NewsModel();
        newsList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(mView.getActivityContext(), LinearLayoutManager.VERTICAL, false);
    }

    @Override
    public void buildList(RecyclerView recyclerView) {
        getData();
        newsAdapter = new NewsAdapter(newsList, mView.getActivityContext());
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setLayoutManager(layoutManager);
        OverScrollDecoratorHelper.setUpOverScroll(recyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
        newsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        curPageNum = 0;
        newsList.clear();
        getData();
    }

    @Override
    public void onLoadMore() {
        getData();
    }

    public void getData() {

        Map<String, Object> params = new HashMap<>();
        params.put("pageNum", curPageNum);
        params.put("pageSize", JobModel.DEFALT_JOB_PAGESIZE);
        if (newsTag != -1) {
            params.put("newsTag", newsTag);
        }
        if (newsType != -1) {
            params.put("newsType", newsType);
        }
        if (!newsAuthor.equals("")) {
            params.put("newsAuthor", newsAuthor);
        }

        newsModel.getNewsListByCondition(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseObject<List<CareerNews>>>() {
                    @Override
                    public void onCompleted() {
                        mView.onRefreshFinished();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onRefreshFinished();
                    }

                    @Override
                    public void onNext(ResponseObject<List<CareerNews>> listResponseObject) {
                        switch (listResponseObject.status) {
                            case 0:
                                ToastUtil.showToast("没有更多数据!");
                                break;
                            case 4:
                                break;
                            case 1:
                                newsList.addAll(listResponseObject.getResult());
                                newsAdapter.notifyDataSetChanged();
                                curPageNum++;
                                break;
                        }
                    }
                });
    }
}
