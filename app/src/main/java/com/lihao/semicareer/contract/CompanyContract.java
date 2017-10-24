package com.lihao.semicareer.contract;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

/**
 * Created by lihao on 2017/8/15.
 */

public interface CompanyContract {

    interface CompanyView {
        Context getActivityContext();

        void setCanLoadMore(boolean canLoadMore);
    }

    interface CompanyPresenter {
        void init();

        void loadMoreData();

        void buildList(RecyclerView recyclerView);
    }
}
