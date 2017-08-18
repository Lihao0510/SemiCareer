package com.lihao.semicareer.contract;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

/**
 * Created by lihao on 2017/8/18.
 */

public interface MyContract {

    interface MyView {
        Context getActivityContext();
        void updateMessageStatus(int lookNum, int interviewNum, int messageNum);
    }

    interface MyPresenter {
        void init();
        void getMessageCount();
    }
}
