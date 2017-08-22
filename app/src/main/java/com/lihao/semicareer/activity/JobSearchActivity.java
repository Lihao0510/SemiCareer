package com.lihao.semicareer.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.lihao.semicareer.R;
import com.oridway.oridcore.utils.ToastUtil;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.BindView;

public class JobSearchActivity extends SwipeBackBaseActivity {

    @BindView(R.id.tfl_job_hot)
    TagFlowLayout tagFlowLayout;
    @BindView(R.id.rv_search_history)
    RecyclerView recyclerView;
    @BindView(R.id.tv_search_ok)
    TextView searchButton;
    @BindView(R.id.et_search_text)
    EditText searchTextView;
    @BindView(R.id.tv_city_choose)
    TextView chooseCityText;
    @BindView(R.id.itv_city_choose)
    IconTextView chooseCityIcon;

    private String cityCode = "";


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, JobSearchActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_search_job;
    }

    @Override
    protected void initActivity() {
        initView();
        initClick();
    }

    private void initView() {
    }

    private void initClick() {
        chooseCityText.setOnClickListener(this);
        chooseCityIcon.setOnClickListener(this);
        searchButton.setOnClickListener(this);
    }

    @Override
    protected boolean changeStatusbarColor() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search_ok:
                goSearchPage();
                break;
            case R.id.tv_city_choose:
                showCitySelect();
                break;
            case R.id.itv_city_choose:
                showCitySelect();
                break;
        }
    }

    private void goSearchPage() {

        String searchLine = searchTextView.getText().toString().trim();
        if (searchLine.equals("")) {
            ToastUtil.showToast("请输入要搜索的职位!");
            return;
        }

        JobListActivity.startActivity(this, searchLine, cityCode);
    }

    private void showCitySelect() {

    }
}
