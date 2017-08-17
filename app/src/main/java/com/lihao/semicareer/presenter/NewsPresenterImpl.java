package com.lihao.semicareer.presenter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lihao.semicareer.adapter.NewsAdapter;
import com.lihao.semicareer.contract.NewsContract;
import com.lihao.semicareer.entity.CareerNews;
import com.lihao.semicareer.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihao on 2017/8/17.
 */

public class NewsPresenterImpl implements NewsContract.NewsPresenter {

    private NewsContract.NewsView mView;
    private LinearLayoutManager layoutManager;
    private List<CareerNews> newsList;
    private NewsAdapter newsAdapter;
    private NewsModel newsModel;

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

    }

    @Override
    public void onRefresh() {

    }
}
