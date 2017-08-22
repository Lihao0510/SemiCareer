package com.lihao.semicareer.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lihao.semicareer.R;

public class LoginActivity extends BaseActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initActivity() {

    }

    @Override
    protected boolean changeStatusbarColor() {
        return false;
    }
}
