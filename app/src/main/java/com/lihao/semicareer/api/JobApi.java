package com.lihao.semicareer.api;

import com.lihao.semicareer.entity.CareerJob;
import com.oridway.oridcore.network.ResponseObject;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lihao on 2017/8/15.
 */

public interface JobApi {

    @GET("/career/queryjoblist")
    Observable<ResponseObject<List<CareerJob>>> queryJobList(@Query("pageNum") int pageNum, @Query("pageSize") int pageSize);

    @GET("/career/getjobdetail")
    Observable<ResponseObject<CareerJob>> getJobDetailByID(@Query("jobID") int jobID);

}
