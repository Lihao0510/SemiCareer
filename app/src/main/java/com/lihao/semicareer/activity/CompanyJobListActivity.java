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

public class CompanyJobListActivity extends SwipeBackBaseActivity {

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
    private int curPageNum = 0;
    private boolean canLoadMore = true;
    private int companyID;


    public static void startActivity(Context context, int companyID) {
        Intent intent = new Intent(context, CompanyJobListActivity.class);
        intent.putExtra("companyID", companyID);
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
        companyID = getIntent().getIntExtra("companyID", 100001);
        jobAdapter = new MainJobAdapter(resultJobList, this);
        listView.setAdapter(jobAdapter);
        listView.setLayoutManager(layoutManager);
    }

    private void initView() {
        titleText.setText("该公司发布的职位");
        OverScrollDecoratorHelper.setUpOverScroll(listView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
    }

    private void getResultList() {


        jobModel.queryJobListByCompanyID(companyID)
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
