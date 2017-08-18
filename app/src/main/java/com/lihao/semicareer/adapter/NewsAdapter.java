package com.lihao.semicareer.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lihao.semicareer.R;
import com.lihao.semicareer.application.CoreApplication;
import com.lihao.semicareer.entity.CareerNews;
import com.oridway.oridcore.eventmessage.ListEvent;
import com.oridway.oridcore.tools.GlideApp;
import com.oridway.oridcore.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by lihao on 2017/8/17.
 */

public class NewsAdapter extends BaseQuickAdapter<CareerNews, BaseViewHolder> {

    public NewsAdapter(@Nullable List<CareerNews> data) {
        super(R.layout.item_news_desc, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CareerNews item) {
        helper.setText(R.id.tv_news_title, item.getNewsTitle());
        GlideApp.with(CoreApplication.getGlobalContext()).load(item.getNewsPic()).centerCrop().into((ImageView) helper.itemView.findViewById(R.id.iv_news_pic));
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        if (helper.getAdapterPosition() == getData().size() - 1) {
            LogUtil.debugLog("触发加载更多:" + helper.getAdapterPosition());
            EventBus.getDefault().post(ListEvent.newEvent(ListEvent.NEWS_LIST_LOADMORE));
        }
    }
}
