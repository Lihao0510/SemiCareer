package com.lihao.semicareer.contract;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

/**
 * Created by lihao on 2017/8/15.
 */

public interface NewsContract {

    interface NewsView {
        Context getActivityContext();

        void onRefreshFinished();
    }

    interface NewsPresenter {
        void init();

        void buildList(RecyclerView recyclerView);

        void onRefresh();

        void onLoadMore();
    }
}
