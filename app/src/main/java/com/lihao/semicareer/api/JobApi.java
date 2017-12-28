package com.lihao.semicareer.api;

import com.lihao.semicareer.entity.CareerCompany;
import com.lihao.semicareer.entity.CareerJob;
import com.oridway.oridcore.network.ResponseObject;

import java.util.List;
import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lihao on 2017/8/15.
 */
public interface JobApi {

    /**
     * Query job list observable.
     *
     * @param pageNum  the page num
     * @param pageSize the page size
     * @return the observable
     */
    @GET("/career/queryjoblist")
    Observable<ResponseObject<List<CareerJob>>> queryJobList(@Query("pageNum") int pageNum, @Query("pageSize") int pageSize);

    /**
     * Gets job detail by id.
     *
     * @param jobID the job id
     * @return the job detail by id
     */
    @GET("/career/getjobdetail")
    Observable<ResponseObject<CareerJob>> getJobDetailByID(@Query("jobID") int jobID);

    /**
     * Gets job list by company.
     *
     * @param companyID the company id
     * @return the job list by company id
     */
    @GET("/career/querybycompany")
    Observable<ResponseObject<List<CareerJob>>> queryJobByCompanyID(@Query("companyID") int companyID);

    /**
     * Query company list observable.
     *
     * @param params the params
     *               start: 开始行数
     *               limit: 返回的最大行数
     *               copanyCity: (可选)城市编码
     *               searchLine: (可选)查询字符串
     *               companyType: (可选)公司类型
     *               companyTag: (可选)公司行业
     * @return the observable
     */
    @POST("/career/querycompany")
    @FormUrlEncoded
    Observable<ResponseObject<List<CareerCompany>>> queryCompanyList(@FieldMap Map<String, Object> params);

    /**
     * Gets company detail by id.
     *
     * @param companyID the company id
     * @return the company detail by id
     */
    @GET("/career/getcompanydetail")
    Observable<ResponseObject<CareerCompany>> getCompanyDetailByID(@Query("companyID") int companyID);

    /**
     * Search job list observable.
     *
     * @param params the params
     *               start: 开始行数
     *               limit: 返回的最大行数
     *               cityCode: (可选)城市编码
     *               searchLine: (可选)查询字符串
     * @return the observable
     */
    @POST("/career/searchjoblist")
    @FormUrlEncoded
    Observable<ResponseObject<List<CareerJob>>> searchJobList(@FieldMap Map<String, Object> params);


    /*
    * @param searchMap需要包含的参数为:
    * start: 开始行数
    * limit: 返回的最大行数
    * cityCode: (可选)城市编码
    * jobType: (可选)查询字符串
    * */
    @POST("/career/getrecommand")
    @FormUrlEncoded
    Observable<ResponseObject<List<CareerJob>>> recommandJobList(@FieldMap Map<String, Object> params);

}
