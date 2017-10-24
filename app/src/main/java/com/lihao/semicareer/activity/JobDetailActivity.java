package com.lihao.semicareer.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.lihao.semicareer.R;
import com.lihao.semicareer.adapter.RecommendJobAdapter;
import com.lihao.semicareer.application.CoreApplication;
import com.lihao.semicareer.entity.CareerJob;
import com.lihao.semicareer.model.JobModel;
import com.lihao.semicareer.model.ResumeModel;
import com.maning.mndialoglibrary.MProgressDialog;
import com.maning.mndialoglibrary.MStatusDialog;
import com.oridway.oridcore.network.ResponseObject;
import com.oridway.oridcore.tools.GlideApp;
import com.oridway.oridcore.utils.Constant;
import com.oridway.oridcore.utils.LogUtil;
import com.oridway.oridcore.utils.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class JobDetailActivity extends SwipeBackBaseActivity {

    public static final String FAVORATE_CHOOSE = "{ion-android-favorite}";
    public static final String FAVORATE_UNCHOOSE = "{ion-android-favorite-outline}";
    public static final String JOB_LOCATION = "{md-place}";
    public static final String JOB_EDUCATION = "{md-card-travel}";
    public static final String JOB_EXPERENCE = "{md-restore}";


    @BindView(R.id.itv_toolbar_back)
    IconTextView backIcon;
    @BindView(R.id.itv_toolbar_right)
    IconTextView rightIcon;
    @BindView(R.id.itv_job_gocompany)
    IconTextView goCompanyIcon;
    @BindView(R.id.tv_toolbar_title)
    TextView titleText;
    @BindView(R.id.sv_jobdetail_container)
    ScrollView container;

    @BindView(R.id.tv_job_name)
    TextView jobName;
    @BindView(R.id.tv_job_salary)
    TextView jobSalary;
    @BindView(R.id.itv_job_attr)
    IconTextView jobAttribute;
    @BindView(R.id.tv_job_advance)
    TextView jobAdvance;
    @BindView(R.id.tv_job_company_name)
    TextView companyName;
    @BindView(R.id.tv_job_company_desc)
    TextView companyDesc;
    @BindView(R.id.iv_job_company_logo)
    ImageView companyLogo;
    @BindView(R.id.tv_job_jd)
    TextView jobDescription;
    @BindView(R.id.tv_job_address)
    TextView jobAddress;
    @BindView(R.id.rv_job_recommend)
    RecyclerView jobRecommendList;
    @BindView(R.id.bt_send_resume)
    Button sendResume;

    private JobModel jobModel;
    private ResumeModel resumeModel;
    private int jobID;
    private LinearLayoutManager layoutManager;
    private List<CareerJob> recommendList;
    private CareerJob jobDetailEntity;
    private RecommendJobAdapter recommendAdapter;

    private MStatusDialog mStatusDialog;
    private MProgressDialog mProgressDialog;


    public static void startActivity(Context context, int jobID) {
        Intent intent = new Intent(context, JobDetailActivity.class);
        intent.putExtra("jobID", jobID);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_job_detail;
    }

    @Override
    protected void initActivity() {
        jobModel = new JobModel();
        resumeModel = new ResumeModel();
        jobID = getIntent().getIntExtra("jobID", 100001);
        initView();
        initClickListener();
        getJobDetail();
    }


    private void initView() {
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        titleText.setText("职位详情");
        rightIcon.setVisibility(View.VISIBLE);
        rightIcon.setText(FAVORATE_UNCHOOSE);
        OverScrollDecoratorHelper.setUpOverScroll(container);
    }

    private void initClickListener() {
        backIcon.setOnClickListener(this);
        rightIcon.setOnClickListener(this);
        goCompanyIcon.setOnClickListener(this);
        sendResume.setOnClickListener(this);
    }

    private void getJobDetail() {

        jobModel.getJobDetailByID(jobID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseObject<CareerJob>>() {
                    @Override
                    public void onCompleted() {
                        getRecommendList();
                        showJobDetail();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseObject<CareerJob> careerJobResponseObject) {
                        switch (careerJobResponseObject.status) {
                            case 0:
                                LogUtil.debugLog("没有该职位信息!");
                                break;
                            case 4:
                                LogUtil.debugLog("获取职位信息失败!");
                                break;
                            case 1:
                                jobDetailEntity = careerJobResponseObject.getResult();
                                break;
                        }
                    }
                });
    }

    public void getRecommendList() {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNum", 0);
        params.put("pageSize", 3);
        jobModel.recommandJobList(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseObject<List<CareerJob>>>() {
                    @Override
                    public void onCompleted() {
                        buildRecommendList();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseObject<List<CareerJob>> listResponseObject) {
                        switch (listResponseObject.status) {
                            case 0:
                                LogUtil.debugLog("没有该职位信息!");
                                break;
                            case 4:
                                LogUtil.debugLog("获取职位信息失败!");
                                break;
                            case 1:
                                recommendList = listResponseObject.getResult();
                                break;
                        }
                    }
                });
    }

    public void showJobDetail() {

        jobName.setText(jobDetailEntity.jobCName);
        jobSalary.setText(jobDetailEntity.jobSalary);
        jobAttribute.setText(JOB_LOCATION + " " + jobDetailEntity.cityName + " | " + jobDetailEntity.districtName + " " + JOB_EXPERENCE + Constant.jobExpType[jobDetailEntity.jobExp] + " " + JOB_EDUCATION + Constant.jobEduType[jobDetailEntity.jobEdu]);
        jobAdvance.setText(jobDetailEntity.jobAddress);
        companyName.setText(jobDetailEntity.companyDetail.companyName);
        companyDesc.setText(jobDetailEntity.companyDetail.companyDesc);
        jobDescription.setText(jobDetailEntity.jobDescribe);
        jobAddress.setText(jobDetailEntity.jobAddress);

        GlideApp.with(this).load(jobDetailEntity.companyDetail.companyLogo).into(companyLogo);

    }

    public void buildRecommendList() {
        recommendAdapter = new RecommendJobAdapter(recommendList);
        jobRecommendList.setAdapter(recommendAdapter);
        jobRecommendList.setLayoutManager(layoutManager);
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
            case R.id.itv_toolbar_right:
                collectJob();
                break;
            case R.id.itv_job_gocompany:
                CompanyDetailActivity.startActivity(this, jobDetailEntity.companyID);
                break;
            case R.id.bt_send_resume:
                sendResume();
                break;
        }
    }

    private void collectJob() {
        if (CoreApplication.loginStatus) {
            showProgressDialog();
            resumeModel.collectJob(CoreApplication.appLoginMessage.userID, jobID)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ResponseObject<String>>() {
                        @Override
                        public void onCompleted() {
                            mProgressDialog.dismiss();
                            rightIcon.setText(FAVORATE_CHOOSE);
                        }

                        @Override
                        public void onError(Throwable e) {
                            showSendFailDialog("收藏失败");
                            mProgressDialog.dismiss();
                        }

                        @Override
                        public void onNext(ResponseObject<String> stringResponseObject) {
                            switch (stringResponseObject.status) {
                                case 0:
                                    showSendFailDialog("已收藏过该职位");
                                    break;
                                case 4:
                                    showSendFailDialog("已收藏过该职位");
                                    break;
                                case 1:
                                    showSendSuccessDialog("收藏成功");
                                    break;
                            }
                        }
                    });
        } else {
            showNotLoginDialog();
        }
    }

    private void sendResume() {
        if (CoreApplication.loginStatus) {
            showProgressDialog();
            resumeModel.sendResume(CoreApplication.appLoginMessage.userID, jobID)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ResponseObject<String>>() {
                        @Override
                        public void onCompleted() {
                            mProgressDialog.dismiss();
                        }

                        @Override
                        public void onError(Throwable e) {
                            showSendFailDialog("投递失败");
                            mProgressDialog.dismiss();
                        }

                        @Override
                        public void onNext(ResponseObject<String> stringResponseObject) {
                            switch (stringResponseObject.status) {
                                case 0:
                                    showSendFailDialog("已投递过简历");
                                    break;
                                case 4:
                                    showSendFailDialog("已投递过简历");
                                    break;
                                case 1:
                                    showSendSuccessDialog("投递成功");
                                    break;
                            }
                        }
                    });
        } else {
            showNotLoginDialog();
        }

    }

    private void showSendSuccessDialog(String msg) {
        mStatusDialog = new MStatusDialog(this);
        mStatusDialog.showSuccess(msg);
    }

    private void showProgressDialog() {
        mProgressDialog = new MProgressDialog(this);
        mProgressDialog.show("投递中");
    }

    private void showNotLoginDialog() {
        mStatusDialog = new MStatusDialog(this);
        mStatusDialog.showError("您尚未登录");
    }

    private void showSendFailDialog(String msg) {
        mStatusDialog = new MStatusDialog(this);
        mStatusDialog.showError(msg);
    }
}
