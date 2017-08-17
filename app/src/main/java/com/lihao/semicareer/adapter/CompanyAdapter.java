package com.lihao.semicareer.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lihao.semicareer.R;
import com.lihao.semicareer.application.CoreApplication;
import com.lihao.semicareer.entity.CareerCompany;
import com.oridway.oridcore.tools.GlideApp;

import java.util.List;

import static com.lihao.semicareer.adapter.JobAdapter.companyType;
import static com.lihao.semicareer.adapter.JobAdapter.getCompanySize;

/**
 * Created by lihao on 2017/8/16.
 */

public class CompanyAdapter extends BaseQuickAdapter<CareerCompany, BaseViewHolder> {

    public CompanyAdapter(@Nullable List<CareerCompany> data) {
        super(R.layout.item_company_desc, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CareerCompany item) {
        helper.setText(R.id.tv_company_name, item.companyName)
                .setText(R.id.tv_company_address, item.companyAddress)
                .setText(R.id.tv_company_desc, item.companyDesc + " | " + getCompanySize(item.companyCareer) + " | " + companyType[item.companyType])
                .setText(R.id.tv_company_job, "提供了" + item.companyTag + "个职位,前往申请");
        GlideApp.with(CoreApplication.getGlobalContext()).load(item.companyLogo).into((ImageView) helper.itemView.findViewById(R.id.iv_company_logo));
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
