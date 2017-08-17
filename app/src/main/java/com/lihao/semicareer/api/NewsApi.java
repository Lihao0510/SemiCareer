package com.lihao.semicareer.api;

import com.lihao.semicareer.entity.CareerNews;
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
 * Created by lihao on 2017/8/17.
 */
public interface NewsApi {

    /**
     * Gets news list by condition.
     *
     * @param params the params
     * 必选属性: pageNum(int), pageSize(int)
     * 可选属性: newsType(int), newsTag(int), newsAuthor(String)
     * @return the news list by condition
     * @Author Lihao 20170818
     */
    @POST("/news/querybycondition")
    @FormUrlEncoded
    Observable<ResponseObject<List<CareerNews>>> getNewsListByCondition(@FieldMap Map<String, Object> params);

    /**
     * Gets news detail by id.
     *
     * @param newsID the news id
     * @return the news detail by id
     * @Author Lihao 20170818
     */
    @GET("/news/getdetail")
    Observable<ResponseObject<CareerNews>> getNewsDetailByID(@Query("newsID") int newsID);

    /**
     * Add comment by news id observable.
     *
     * @param params the params
     * 必选: newsID(int), userID(int), commentText(String)
     * @return the Status
     */
    @POST("/news/addcomment")
    @FormUrlEncoded
    Observable<ResponseObject<String>> addCommentByNewsID(@FieldMap Map<String, Object> params);
}
