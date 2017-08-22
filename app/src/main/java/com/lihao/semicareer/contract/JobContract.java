package com.lihao.semicareer.contract;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by lihao on 2017/8/13.
 */

public interface JobContract {

    interface JobView {
        Context getActivityContext();

        void onFinishRefresh();

        void setCanLoadMore(boolean canLoadMore);
    }

    interface JobPresenter {
        void init();

        void buildList(RecyclerView recyclerView);

        void setBanner(View view);

        void loadMoreData();

        void refreshData();
    }
}
