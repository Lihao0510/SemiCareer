package com.lihao.semicareer.model;

import com.lihao.semicareer.api.JobApi;
import com.lihao.semicareer.entity.CareerCompany;
import com.oridway.oridcore.network.ResponseObject;
import com.oridway.oridcore.network.RetrofitManager;

import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * Created by lihao on 2017/8/16.
 */

public class CompanyModel {

    public static final int DEFALT_COMPANY_PAGESIZE = 5;

    public Observable<ResponseObject<List<CareerCompany>>> queryCompanyByCondition(Map<String, Object> params) {
        return RetrofitManager
                .getInstance()
                .createReq(JobApi.class)
                .queryCompanyList(params);
    }

    public Observable<ResponseObject<CareerCompany>> getCompanyDetailByID(int companyID) {
        return RetrofitManager
                .getInstance()
                .createReq(JobApi.class)
                .getCompanyDetailByID(companyID);
    }
}
