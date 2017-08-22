package com.lihao.semicareer.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lihao.semicareer.R;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.BindView;

public class CompanySearchActivity extends SwipeBackBaseActivity {

    @BindView(R.id.tfl_company_hot)
    TagFlowLayout tagFlowLayout;
    @BindView(R.id.rv_search_history)
    RecyclerView recyclerView;
    @BindView(R.id.tv_search_ok)
    TextView searchButton;
    @BindView(R.id.et_search_text)
    EditText searchTextView;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CompanySearchActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_company_search;
    }

    @Override
    protected void initActivity() {

    }

    @Override
    protected boolean changeStatusbarColor() {
        return false;
    }

    @Override
    public void onClick(View v) {

    }
}
