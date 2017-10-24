package com.lihao.semicareer.api;

import com.lihao.semicareer.entity.CareerMessage;
import com.oridway.oridcore.network.ResponseObject;

import java.util.List;
import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lihao on 2017/8/18.
 */

public interface MessageApi {

    @POST("/semi/message/query")
    @FormUrlEncoded
    Observable<ResponseObject<List<CareerMessage>>> queryMessageListByCondition(@FieldMap Map<String, Object> params);

    @GET("/semi/message/detail")
    Observable<ResponseObject<CareerMessage>> getMessageDetailByID(@Query("messageID") int messageID);

    @POST("/semi/message/write")
    @FormUrlEncoded
    Observable<ResponseObject<String>> writeMessage(@FieldMap Map<String, Object> params);

    @POST("/semi/message/changestatus")
    @FormUrlEncoded
    Observable<ResponseObject<String>> changeMessageStatus(@Field("messageID") int messageID, @Field("messageTag") int messageTag);

    @GET("/semi/message/delete")
    Observable<ResponseObject<String>> deleteMessageByID(@Query("messageID") int messageID);
}
