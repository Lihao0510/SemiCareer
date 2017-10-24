package com.lihao.semicareer.api;

import com.lihao.semicareer.entity.LoginMessage;
import com.oridway.oridcore.network.ResponseObject;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by lihao on 2017/8/18.
 */

public interface LoginApi {

    @POST("/semi/launch/careerlogin")
    @FormUrlEncoded
    Observable<ResponseObject<LoginMessage>> loginByPhone(@FieldMap Map<String, Object> params);

}
