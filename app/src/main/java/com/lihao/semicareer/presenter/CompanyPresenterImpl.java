package com.lihao.semicareer.presenter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lihao.semicareer.adapter.MainCompanyAdapter;
import com.lihao.semicareer.contract.CompanyContract;
import com.lihao.semicareer.entity.CareerCompany;
import com.lihao.semicareer.model.CompanyModel;
import com.lihao.semicareer.model.JobModel;
import com.oridway.oridcore.network.ResponseObject;
import com.oridway.oridcore.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lihao on 2017/8/16.
 */

public class CompanyPresenterImpl implements CompanyContract.CompanyPresenter {

    private CompanyContract.CompanyView mView;
    private CompanyModel companyModel;
    private JobModel jobModel;

    private MainCompanyAdapter mAdapter;
    private List<CareerCompany> companyList;
    private LinearLayoutManager layoutManager;

    private int curPage;
    private String curCompanyName = "";
    private String curCompanyCity = "";
    private int curCompanyType = -1;
    private int curCompanyTag = -1;

    public CompanyPresenterImpl(CompanyContract.CompanyView view) {
        this.mView = view;
        init();
    }

    @Override
    public void init() {
        jobModel = new JobModel();
        companyModel = new CompanyModel();
        companyList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(mView.getActivityContext(), LinearLayoutManager.VERTICAL, false);
    }

    @Override
    public void loadMoreData() {
        getCompanyList(curCompanyName, curCompanyCity, curCompanyTag, curCompanyType);
    }

    @Override
    public void buildList(RecyclerView recyclerView) {
        getCompanyList("", "", -1, -1);
        mAdapter = new MainCompanyAdapter(companyList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(layoutManager);
        OverScrollDecoratorHelper.setUpOverScroll(recyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
        mAdapter.notifyDataSetChanged();
    }

    private void getCompanyList(String searchLine, final String companyCity, int companyTag, int companyType) {

        Map<String, Object> params = new HashMap<>();
        params.put("pageNum", curPage);
        params.put("pageSize", JobModel.DEFALT_JOB_PAGESIZE);
        params.put("companyCity", companyCity);
        params.put("searchLine", searchLine);

        if (companyTag >= 0) {
            params.put("companyTag", companyTag);
        }
        if (companyType >= 0) {
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
                                break;
                            case 4:

                                break;
                            case 1:
                                companyList.addAll(listResponseObject.getResult());
                                mAdapter.notifyDataSetChanged();
                                curPage++;
                                break;
                        }
                    }
                });
    }
}
