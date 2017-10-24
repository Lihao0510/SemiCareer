package com.lihao.semicareer.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.lihao.semicareer.R;
import com.lihao.semicareer.application.CoreApplication;
import com.lihao.semicareer.entity.CareerNews;
import com.lihao.semicareer.model.NewsModel;
import com.maning.mndialoglibrary.MStatusDialog;
import com.oridway.oridcore.network.ResponseObject;
import com.oridway.oridcore.tools.GlideApp;

import butterknife.BindView;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewsDetailActivity extends SwipeBackBaseActivity {

    @BindView(R.id.itv_toolbar_back)
    IconTextView backIcon;
    @BindView(R.id.itv_toolbar_right)
    IconTextView rightIcon;
    @BindView(R.id.tv_toolbar_title)
    TextView titleText;
    @BindView(R.id.bt_login_action)
    Button loginAction;
    @BindView(R.id.sv_newsdetail_container)
    ScrollView container;
    @BindView(R.id.tv_news_title)
    TextView newsTitle;
    @BindView(R.id.tv_news_detail)
    TextView newsContent;
    @BindView(R.id.tv_news_author)
    TextView newsAuthor;
    @BindView(R.id.tv_news_time)
    TextView newsTime;
    @BindView(R.id.rv_news_comment)
    RecyclerView commentListView;
    @BindView(R.id.iv_newsdetail_pic)
    ImageView newsPic;

    private MStatusDialog mStatusDialog;
    private int newsID;
    private NewsModel newsModel;

    public static void startActivity(Context context, int newsID) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra("newsID", newsID);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void initActivity() {
        initData();
        initView();
        initClick();
        getNewsDetail();
    }

    private void initData() {
        newsModel = new NewsModel();
        newsID = getIntent().getIntExtra("newsID", 100001);
    }

    private void initView() {
        titleText.setText("新闻资讯");
        OverScrollDecoratorHelper.setUpOverScroll(container);
    }

    private void initClick() {
        backIcon.setOnClickListener(this);
        loginAction.setOnClickListener(this);
    }

    private void getNewsDetail() {
        newsModel.getNewsDetailByID(newsID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseObject<CareerNews>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showErrorDialog("获取新闻失败");
                    }

                    @Override
                    public void onNext(ResponseObject<CareerNews> careerNewsResponseObject) {
                        switch (careerNewsResponseObject.status) {
                            case 0:
                                break;
                            case 4:
                                break;
                            case 1:
                                buildNewsData(careerNewsResponseObject.getResult());
                                break;
                        }
                    }
                });
    }

    private void buildNewsData(CareerNews newsResult) {
        newsTitle.setText(newsResult.newsTitle);
        newsAuthor.setText("作者: " + newsResult.newsAuthor);
        newsTime.setText(newsResult.newsTime.substring(0, 10));
        newsContent.setText(newsResult.newsContent);
        GlideApp.with(this).load(newsResult.newsPic).into(newsPic);
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
            case R.id.bt_login_action:

                break;
        }
    }


    private void showSendSuccessDialog(String msg) {
        mStatusDialog = new MStatusDialog(this);
        mStatusDialog.showSuccess(msg);
    }

    private void showErrorDialog(String msg) {
        mStatusDialog = new MStatusDialog(this);
        mStatusDialog.showError(msg);
    }
}
