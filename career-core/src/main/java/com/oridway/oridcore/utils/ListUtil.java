package com.oridway.oridcore.utils;

import android.support.v7.widget.RecyclerView;

/**
 * Created by lihao on 2017/8/22.
 */

public class ListUtil {

    public static boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }


}
