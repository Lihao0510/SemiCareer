package com.lihao.semicareer.presenter;

import android.app.Notification;

import com.lihao.semicareer.contract.MyContract;
import com.lihao.semicareer.model.MessageModel;

/**
 * Created by lihao on 2017/8/18.
 */

public class MyPresenterImpl implements MyContract.MyPresenter {

    private MyContract.MyView mView;
    private MessageModel messageModel;

    public MyPresenterImpl(MyContract.MyView view) {
        this.mView = view;
        init();
    }

    @Override
    public void init() {
        messageModel = new MessageModel();
    }

    @Override
    public void getMessageCount() {

    }
}
