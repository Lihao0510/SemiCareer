package com.lihao.semicareer.model;

import com.lihao.semicareer.api.ResumeApi;
import com.lihao.semicareer.entity.CareerJob;
import com.oridway.oridcore.network.ResponseObject;
import com.oridway.oridcore.network.RetrofitManager;

import java.util.List;

import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lihao on 2017/8/18.
 */

public class ResumeModel {

    public Observable<ResponseObject<String>> sendResume(int userID, int jobID) {
        return RetrofitManager
                .getInstance()
                .createReq(ResumeApi.class)
                .sendResume(userID, jobID);
    }

    public Observable<ResponseObject<String>> collectJob(int userID, int jobID) {
        return RetrofitManager
                .getInstance()
                .createReq(ResumeApi.class)
                .collectJob(userID, jobID);
    }

    public Observable<ResponseObject<List<CareerJob>>> getCollectJobByUserID(int userID){
        return RetrofitManager
                .getInstance()
                .createReq(ResumeApi.class)
                .getCollectJobByUserID(userID);
    }

    public Observable<ResponseObject<List<CareerJob>>> getSendHistory(int userID){
        return RetrofitManager
                .getInstance()
                .createReq(ResumeApi.class)
                .getSendHistory(userID);
    }
}
