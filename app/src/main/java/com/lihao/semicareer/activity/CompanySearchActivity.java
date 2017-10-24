package com.lihao.semicareer.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lihao.semicareer.R;
import com.oridway.oridcore.utils.ToastUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
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
    @BindView(R.id.ll_select_district)
    LinearLayout selectCity;
    @BindView(R.id.ll_select_type)
    LinearLayout selectType;
    @BindView(R.id.tv_city_text)
    TextView cityText;
    @BindView(R.id.tv_type_text)
    TextView typeText;
    @BindView(R.id.cl_searchbar_layout)
    ConstraintLayout searchBarLayout;

    private String cityCode = "";

    private PopupWindow cityPopupWindow;
    private PopupWindow typePopupWindow;

    private String[] cityList = {"全国", "北京", "上海", "深圳", "武汉", "鄂尔多斯", "合肥", "厦门", "广州", "苏州", "西安", "重庆"};
    private String[] hotCompany = {"京东方", "华星光电", "信利", "HKC惠科", "中电熊猫", "天马", "友达光电", "ASML(上海)"};

    private String copanyCity = "";
    private String searchLine = "";
    private int companyType = 0;
    private int companyTag = 0;

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
        initView();
        initClick();
    }

    private void initView() {
        initHotCompany();
    }

    private void initClick() {
        selectCity.setOnClickListener(this);
        selectType.setOnClickListener(this);
        searchButton.setOnClickListener(this);
    }

    private void initHotCompany() {
        tagFlowLayout.setAdapter(new TagAdapter<String>(hotCompany) {
            @Override
            public View getView(FlowLayout parent, int position, String o) {
                View content = LayoutInflater.from(CompanySearchActivity.this).inflate(R.layout.item_tag_text, null);
                TextView tv = (TextView) content.findViewById(R.id.tv_city_tag);
                tv.setText(hotCompany[position]);
                return content;
            }
        });
        tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                searchTextView.setText(hotCompany[position]);
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
                goSearchList();
                break;
            case R.id.ll_select_district:
                showCitySelect();
                break;
            case R.id.ll_select_type:
                showTypeSelect();
                break;
        }
    }

    private void goSearchList() {
        String searchLine = searchTextView.getText().toString().trim();
        if (searchLine.equals("")) {
            ToastUtil.showToast("请输入要搜索的职位!");
            return;
        }
        CompanyListActivity.startActivity(this, searchLine, copanyCity, companyType, companyTag);
    }

    private void showTypeSelect() {
        if (typePopupWindow == null) {
            View popupView = LayoutInflater.from(this).inflate(R.layout.popup_city_picker, null);
            typePopupWindow = new PopupWindow(this);
            typePopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            typePopupWindow.setHeight(450);
            typePopupWindow.setContentView(popupView);
            typePopupWindow.setOutsideTouchable(false);
            typePopupWindow.setBackgroundDrawable(new ColorDrawable(0xffffffff));
            typePopupWindow.setFocusable(true);
            TagFlowLayout tflLayout = (TagFlowLayout) popupView.findViewById(R.id.tfl_jobsearch_citypick);
            tflLayout.setAdapter(new TagAdapter<String>(cityList) {
                @Override
                public View getView(FlowLayout parent, int position, String o) {
                    View content = LayoutInflater.from(CompanySearchActivity.this).inflate(R.layout.item_tag_text, null);
                    TextView tv = (TextView) content.findViewById(R.id.tv_city_tag);
                    tv.setText(cityList[position]);
                    return content;
                }
            });
            tflLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    typeText.setText(cityList[position]);
                    typePopupWindow.dismiss();
                    return true;
                }
            });
        }
        typePopupWindow.showAsDropDown(searchBarLayout);

    }

    private void showCitySelect() {
        if (cityPopupWindow == null) {
            View popupView = LayoutInflater.from(this).inflate(R.layout.popup_city_picker, null);
            cityPopupWindow = new PopupWindow(this);
            cityPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            cityPopupWindow.setHeight(450);
            cityPopupWindow.setContentView(popupView);
            cityPopupWindow.setOutsideTouchable(false);
            cityPopupWindow.setBackgroundDrawable(new ColorDrawable(0xffffffff));
            cityPopupWindow.setFocusable(true);
            TagFlowLayout tflLayout = (TagFlowLayout) popupView.findViewById(R.id.tfl_jobsearch_citypick);
            tflLayout.setAdapter(new TagAdapter<String>(cityList) {
                @Override
                public View getView(FlowLayout parent, int position, String o) {
                    View content = LayoutInflater.from(CompanySearchActivity.this).inflate(R.layout.item_tag_text, null);
                    TextView tv = (TextView) content.findViewById(R.id.tv_city_tag);
                    tv.setText(cityList[position]);
                    return content;
                }
            });
            tflLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    cityText.setText(cityList[position]);
                    cityPopupWindow.dismiss();
                    return true;
                }
            });
        }
        cityPopupWindow.showAsDropDown(searchBarLayout);
    }
}
