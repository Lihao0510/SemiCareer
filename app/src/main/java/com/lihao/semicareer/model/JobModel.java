package com.lihao.semicareer.model;

import com.lihao.semicareer.api.JobApi;
import com.lihao.semicareer.entity.CareerJob;
import com.oridway.oridcore.network.ResponseObject;
import com.oridway.oridcore.network.RetrofitManager;

import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * Created by lihao on 2017/8/15.
 */

public class JobModel {

    public static final int DEFALT_PAGESIZE = 12;

    public Observable<ResponseObject<List<CareerJob>>> queryJobList(int pageNum, int pageSize) {
        return RetrofitManager
                .getInstance()
                .createReq(JobApi.class)
                .queryJobList(pageNum, pageSize);
    }

    public Observable<ResponseObject<CareerJob>> getJobDetailByID(int jobID) {
        return RetrofitManager
                .getInstance()
                .createReq(JobApi.class)
                .getJobDetailByID(jobID);
    }

    public Observable<ResponseObject<List<CareerJob>>> queryJobListByCondition(Map<String, Object> params) {
        return RetrofitManager
                .getInstance()
                .createReq(JobApi.class)
                .searchJobList(params);
    }

}
