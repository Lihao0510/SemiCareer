package com.lihao.semicareer.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.lihao.semicareer.R;
import com.lihao.semicareer.adapter.MainJobAdapter;
import com.lihao.semicareer.entity.CareerJob;
import com.lihao.semicareer.model.JobModel;
import com.oridway.oridcore.network.ResponseObject;
import com.oridway.oridcore.utils.ListUtil;
import com.oridway.oridcore.utils.LogUtil;
import com.oridway.oridcore.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class JobListActivity extends SwipeBackBaseActivity {

    @BindView(R.id.itv_toolbar_back)
    IconTextView backIcon;
    @BindView(R.id.tv_toolbar_title)
    TextView titleText;
    @BindView(R.id.rv_job)
    RecyclerView listView;


    private JobModel jobModel;
    private LinearLayoutManager layoutManager;
    private MainJobAdapter jobAdapter;
    private List<CareerJob> resultJobList;
    private String searchLine;
    private String cityCode;
    private int curPageNum = 0;
    private boolean canLoadMore = true;


    public static void startActivity(Context context, String searchLine, String cityCode) {
        Intent intent = new Intent(context, JobDetailActivity.class);
        intent.putExtra("searchLine", searchLine);
        intent.putExtra("cityCode", cityCode);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_job_list;
    }

    @Override
    protected void initActivity() {
        initData();
        initView();
        initClickListener();
        getResultList();
    }

    private void initData() {
        jobModel = new JobModel();
        resultJobList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        searchLine = getIntent().getStringExtra("searchLine");
        cityCode = getIntent().getStringExtra("cityCode");
        jobAdapter = new MainJobAdapter(resultJobList);
        listView.setAdapter(jobAdapter);
        listView.setLayoutManager(layoutManager);
    }

    private void initView() {
        titleText.setText("搜索结果");
        OverScrollDecoratorHelper.setUpOverScroll(listView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
    }

    private void getResultList() {

        Map<String, Object> params = new HashMap<>();
        params.put("pageNum", curPageNum);
        params.put("pageSize", JobModel.DEFALT_JOB_PAGESIZE);
        if (!searchLine.equals("")) {
            params.put("searchLine", searchLine);
        }
        if (!cityCode.equals("")) {
            params.put("cityCode", cityCode);
        }

        jobModel.queryJobListByCondition(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseObject<List<CareerJob>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseObject<List<CareerJob>> listResponseObject) {
                        switch (listResponseObject.status) {
                            case 0:
                                ToastUtil.showToast("没有更多数据!");
                                canLoadMore = false;
                                break;
                            case 4:

                                break;
                            case 1:
                                resultJobList.addAll(listResponseObject.getResult());
                                jobAdapter.notifyDataSetChanged();
                                curPageNum++;
                                break;
                        }
                    }
                });

    }

    private void initClickListener() {
        backIcon.setOnClickListener(this);
        listView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LogUtil.debugLog("是否滑动到底部:" + ListUtil.isSlideToBottom(recyclerView));
                if (ListUtil.isSlideToBottom(recyclerView) && canLoadMore) {
                    getResultList();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    protected boolean changeStatusbarColor() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.itv_toolbar_back:
                finish();
                break;
        }
    }
}
