package com.lihao.semicareer.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.lihao.semicareer.R;
import com.oridway.oridcore.utils.ToastUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
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
    @BindView(R.id.ll_jobsearch_toolbar)
    LinearLayout searchBarLayout;

    private String cityCode = "";

    private PopupWindow popWindow;

    private String[] cityList = {"全国", "北京", "上海", "深圳", "武汉", "鄂尔多斯", "合肥", "厦门", "广州", "苏州", "西安", "重庆"};
    private String[] hotJob = {"IE工程师", "整合工程师", "法务", "工程师", "技术员", "PVD", "Layout研发", "材料研发", "生产"};

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
        initHotJob();
    }

    private void initClick() {
        chooseCityText.setOnClickListener(this);
        chooseCityIcon.setOnClickListener(this);
        searchButton.setOnClickListener(this);
    }

    private void initHotJob() {
        tagFlowLayout.setAdapter(new TagAdapter<String>(hotJob) {
            @Override
            public View getView(FlowLayout parent, int position, String o) {
                View content = LayoutInflater.from(JobSearchActivity.this).inflate(R.layout.item_tag_text, null);
                TextView tv = (TextView) content.findViewById(R.id.tv_city_tag);
                tv.setText(hotJob[position]);
                return content;
            }
        });
        tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                searchTextView.setText(hotJob[position]);
                return true;
            }
        });
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
        if (popWindow == null) {
            View popupView = LayoutInflater.from(this).inflate(R.layout.popup_city_picker, null);
            popWindow = new PopupWindow(this);
            popWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            popWindow.setHeight(450);
            popWindow.setContentView(popupView);
            popWindow.setOutsideTouchable(false);
            popWindow.setBackgroundDrawable(new ColorDrawable(0xffffffff));
            popWindow.setFocusable(true);
            TagFlowLayout tflLayout = (TagFlowLayout) popupView.findViewById(R.id.tfl_jobsearch_citypick);
            tflLayout.setAdapter(new TagAdapter<String>(cityList) {
                @Override
                public View getView(FlowLayout parent, int position, String o) {
                    View content = (View) LayoutInflater.from(JobSearchActivity.this).inflate(R.layout.item_tag_text, null);
                    TextView tv = (TextView) content.findViewById(R.id.tv_city_tag);
                    tv.setText(cityList[position]);
                    return content;
                }
            });
            tflLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    chooseCityText.setText(cityList[position]);
                    popWindow.dismiss();
                    return true;
                }
            });
        }
        popWindow.showAsDropDown(searchBarLayout);
    }
}
