package com.lihao.semicareer.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.lihao.semicareer.R;
import com.lihao.semicareer.entity.CareerCompany;
import com.lihao.semicareer.model.CompanyModel;
import com.oridway.oridcore.network.ResponseObject;
import com.oridway.oridcore.tools.GlideApp;
import com.oridway.oridcore.utils.LogUtil;

import butterknife.BindView;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CompanyDetailActivity extends SwipeBackBaseActivity {

    @BindView(R.id.itv_toolbar_back)
    IconTextView backIcon;
    @BindView(R.id.tv_toolbar_title)
    TextView titleText;
    @BindView(R.id.sv_company_container)
    ScrollView container;
    @BindView(R.id.tv_company_name)
    TextView companyName;
    @BindView(R.id.tv_company_address)
    TextView companyAddress;
    @BindView(R.id.tv_company_desc)
    TextView companyDesc;
    @BindView(R.id.tv_company_job)
    TextView companyJobNum;
    @BindView(R.id.tv_company_location)
    TextView companyLocation;
    @BindView(R.id.tv_company_detail)
    TextView companyDetail;
    @BindView(R.id.iv_company_logo)
    ImageView companyLogo;
    @BindView(R.id.bt_company_joblist)
    Button companyJobList;


    private int companyID;
    private CompanyModel companyModel;
    private CareerCompany companyDetailEntity;

    public static void startActivity(Context context, int companyID) {
        Intent intent = new Intent(context, CompanyDetailActivity.class);
        intent.putExtra("companyID", companyID);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_company_detail;
    }

    @Override
    protected void initActivity() {
        companyID = getIntent().getIntExtra("companyID", 100001);
        companyModel = new CompanyModel();
        initView();
        initClickListener();
        getCompanyDetail();
    }

    private void initView() {
        titleText.setText("企业详情");
        OverScrollDecoratorHelper.setUpOverScroll(container);
    }

    private void initClickListener() {
        backIcon.setOnClickListener(this);
        companyJobList.setOnClickListener(this);
    }

    private void getCompanyDetail() {

        companyModel.getCompanyDetailByID(companyID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseObject<CareerCompany>>() {
                    @Override
                    public void onCompleted() {
                        showCompanyDetail();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseObject<CareerCompany> careerCompanyResponseObject) {
                        switch (careerCompanyResponseObject.status) {
                            case 0:
                                LogUtil.debugLog("没有该公司信息!");
                                break;
                            case 4:
                                LogUtil.debugLog("获取公司信息失败!");
                                break;
                            case 1:
                                companyDetailEntity = careerCompanyResponseObject.getResult();
                                break;
                        }
                    }
                });
    }

    private void showCompanyDetail() {
        companyName.setText(companyDetailEntity.companyName);
        companyAddress.setText(companyDetailEntity.companyAddress);
        companyDesc.setText(companyDetailEntity.companyDesc);
        companyJobNum.setText("该公司提供了" + companyDetailEntity.companyTag + "个职位");
        companyLocation.setText(companyDetailEntity.companyAddress);
        companyDetail.setText(companyDetailEntity.companyIntro);
        GlideApp.with(this).load(companyDetailEntity.companyLogo).into(companyLogo);
    }

    @Override
    protected boolean changeStatusbarColor() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.itv_toolbar_back:
                finish();
                break;
            case R.id.bt_company_joblist:
                goCompanyJobList();
                break;
        }
    }

    private void goCompanyJobList() {
        CompanyJobListActivity.startActivity(this, companyID);
    }
}
