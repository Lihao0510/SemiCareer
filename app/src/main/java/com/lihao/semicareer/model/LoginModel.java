package com.lihao.semicareer.model;

import com.lihao.semicareer.api.LoginApi;
import com.lihao.semicareer.entity.LoginMessage;
import com.oridway.oridcore.network.ResponseObject;
import com.oridway.oridcore.network.RetrofitManager;

import java.util.Map;

import retrofit2.http.FieldMap;
import rx.Observable;

/**
 * Created by lihao on 2017/8/18.
 */

public class LoginModel {

    public Observable<ResponseObject<LoginMessage>> loginByPhone(Map<String, Object> params) {
        return RetrofitManager
                .getInstance()
                .createReq(LoginApi.class)
                .loginByPhone(params);
    }

}
