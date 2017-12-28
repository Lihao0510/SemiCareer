package com.lihao.semicareer.api;

import com.lihao.semicareer.entity.CareerJob;
import com.oridway.oridcore.network.ResponseObject;


import java.util.List;

import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lihao on 2017/8/18.
 */

public interface ResumeApi {

    @POST("/resume/send")
    @FormUrlEncoded
    Observable<ResponseObject<String>> sendResume(@Field("userID") int userID, @Field("jobID") int jobID);

    @POST("/resume/collect")
    @FormUrlEncoded
    Observable<ResponseObject<String>> collectJob(@Field("userID") int userID, @Field("jobID") int jobID);

    @GET("/resume/getcollect")
    Observable<ResponseObject<List<CareerJob>>> getCollectJobByUserID(@Query("userID") int userID);

    @GET("/resume/sendhistory")
    Observable<ResponseObject<List<CareerJob>>> getSendHistory(@Query("userID") int userID);
}
