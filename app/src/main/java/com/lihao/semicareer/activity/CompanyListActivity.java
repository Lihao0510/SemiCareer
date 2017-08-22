package com.lihao.semicareer.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.lihao.semicareer.R;
import com.lihao.semicareer.adapter.MainCompanyAdapter;
import com.lihao.semicareer.adapter.MainJobAdapter;
import com.lihao.semicareer.entity.CareerCompany;
import com.lihao.semicareer.model.CompanyModel;
import com.lihao.semicareer.model.JobModel;
import com.oridway.oridcore.network.ResponseObject;
import com.oridway.oridcore.utils.ListUtil;
import com.oridway.oridcore.utils.LogUtil;
import com.oridway.oridcore.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CompanyListActivity extends SwipeBackBaseActivity {

    @BindView(R.id.itv_toolbar_back)
    IconTextView backIcon;
    @BindView(R.id.tv_toolbar_title)
    TextView titleText;
    @BindView(R.id.rv_company)
    RecyclerView listView;

    private CompanyModel companyModel;
    private LinearLayoutManager layoutManager;
    private MainCompanyAdapter companyAdapter;
    private List<CareerCompany> companyResultList;
    private int curPageNum = 0;
    private boolean canLoadMore = true;
    private String copanyCity;
    private String searchLine;
    private int companyType;
    private int companyTag;

    public static void startActivity(Context context, String searchLine, String copanyCity, int companyType, int companyTag) {
        Intent intent = new Intent(context, JobDetailActivity.class);
        intent.putExtra("searchLine", searchLine);
        intent.putExtra("copanyCity", copanyCity);
        intent.putExtra("companyType", companyType);
        intent.putExtra("companyTag", companyTag);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_company_list;
    }

    @Override
    protected void initActivity() {
        initData();
        initView();
        initClickListener();
        getCompanyData();
    }

    private void initData() {
        companyModel = new CompanyModel();
        companyResultList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        searchLine = getIntent().getStringExtra("searchLine");
        copanyCity = getIntent().getStringExtra("copanyCity");
        companyType = getIntent().getIntExtra("companyType", -1);
        companyTag = getIntent().getIntExtra("companyTag", -1);
        companyAdapter = new MainCompanyAdapter(companyResultList);
        listView.setAdapter(companyAdapter);
        listView.setLayoutManager(layoutManager);
    }

    private void initView() {
        titleText.setText("搜索结果");
        OverScrollDecoratorHelper.setUpOverScroll(listView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
    }

    /*
    * @param searchMap需要包含的参数为:
    * start: 开始行数
    * limit: 返回的最大行数
    * copanyCity: (可选)城市编码
    * searchLine: (可选)查询字符串
    * companyType: (可选)公司类型
    * companyTag: (可选)公司行业
    * */
    private void getCompanyData() {

        Map<String, Object> params = new HashMap<>();
        params.put("pageNum", curPageNum);
        params.put("pageSize", JobModel.DEFALT_JOB_PAGESIZE);
        if (!searchLine.equals("")) {
            params.put("searchLine", searchLine);
        }
        if (!copanyCity.equals("")) {
            params.put("copanyCity", copanyCity);
        }
        if (companyTag > 0) {
            params.put("companyTag", companyTag);
        }
        if (companyType > 0) {
            params.put("companyType", companyType);
        }

        companyModel.queryCompanyByCondition(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseObject<List<CareerCompany>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseObject<List<CareerCompany>> listResponseObject) {
                        switch (listResponseObject.status) {
                            case 0:
                                ToastUtil.showToast("没有更多数据!");
                                canLoadMore = false;
                                break;
                            case 4:

                                break;
                            case 1:
                                companyResultList.addAll(listResponseObject.getResult());
                                companyAdapter.notifyDataSetChanged();
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
                    getCompanyData();
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
