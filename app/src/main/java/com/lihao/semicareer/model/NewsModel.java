package com.lihao.semicareer.model;

import com.lihao.semicareer.api.NewsApi;
import com.lihao.semicareer.entity.CareerNews;
import com.oridway.oridcore.network.ResponseObject;
import com.oridway.oridcore.network.RetrofitManager;

import java.util.List;
import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lihao on 2017/8/17.
 */

public class NewsModel {

    public static final int DEFALT_NEWS_PAGESIZE = 5;


    public Observable<ResponseObject<List<CareerNews>>> getNewsListByCondition(Map<String, Object> params) {
        return RetrofitManager
                .getInstance()
                .createReq(NewsApi.class)
                .getNewsListByCondition(params);

    }

    public Observable<ResponseObject<CareerNews>> getNewsDetailByID(int newsID) {
        return RetrofitManager
                .getInstance()
                .createReq(NewsApi.class)
                .getNewsDetailByID(newsID);
    }

    public Observable<ResponseObject<String>> addCommentByNewsID(Map<String, Object> params) {
        return RetrofitManager
                .getInstance()
                .createReq(NewsApi.class)
                .addCommentByNewsID(params);
    }


}
