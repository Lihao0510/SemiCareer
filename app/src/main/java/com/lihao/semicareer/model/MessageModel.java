package com.lihao.semicareer.model;

import com.lihao.semicareer.api.MessageApi;
import com.lihao.semicareer.entity.CareerMessage;
import com.oridway.oridcore.network.ResponseObject;
import com.oridway.oridcore.network.RetrofitManager;

import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * Created by lihao on 2017/8/18.
 */

public class MessageModel {

    public static final int DEFALT_MESSAGE_PAGESIZE = 5;


    public Observable<ResponseObject<List<CareerMessage>>> queryMessageListByCondition(Map<String, Object> params) {
        return RetrofitManager
                .getInstance()
                .createReq(MessageApi.class)
                .queryMessageListByCondition(params);
    }

    public Observable<ResponseObject<CareerMessage>> getMessageDetailByID(int messageID) {
        return RetrofitManager
                .getInstance()
                .createReq(MessageApi.class)
                .getMessageDetailByID(messageID);
    }

    public Observable<ResponseObject<String>> writeMessage(Map<String, Object> params) {
        return RetrofitManager
                .getInstance()
                .createReq(MessageApi.class)
                .writeMessage(params);
    }

    public Observable<ResponseObject<String>> changeMessageStatus(int messageID, int messageTag) {
        return RetrofitManager
                .getInstance()
                .createReq(MessageApi.class)
                .changeMessageStatus(messageID, messageTag);
    }

    public Observable<ResponseObject<String>> deleteMessageByID(int messageID) {
        return RetrofitManager
                .getInstance()
                .createReq(MessageApi.class)
                .deleteMessageByID(messageID);
    }

}
