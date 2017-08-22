package com.lihao.semicareer.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lihao.semicareer.R;
import com.lihao.semicareer.application.CoreApplication;
import com.lihao.semicareer.entity.CareerJob;
import com.oridway.oridcore.eventmessage.ListEvent;
import com.oridway.oridcore.tools.GlideApp;
import com.oridway.oridcore.utils.Constant;
import com.oridway.oridcore.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by lihao on 2017/8/13.
 */

public class MainJobAdapter extends BaseQuickAdapter<CareerJob, BaseViewHolder> {


    public MainJobAdapter(@Nullable List<CareerJob> data) {
        super(R.layout.item_job_desc, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final CareerJob item) {
        helper.setText(R.id.tv_job_name, item.jobCName)
                .setText(R.id.tv_job_company_desc, item.companyDetail.companyDesc + " | " + getCompanySize(item.companyDetail.companyCareer) + " | " + Constant.companyType[item.companyDetail.companyType])
                .setText(R.id.tv_job_salary, item.jobSalary)
                .setText(R.id.tv_job_time, item.jobTime.substring(0, 10))
                .setText(R.id.tv_job_company_name, item.companyDetail.companyName)
                .setText(R.id.tv_job_detail, item.cityName + " | " + item.districtName + " | " + Constant.jobEduType[item.jobEdu] + " | " + Constant.jobExpType[item.jobExp]);
        GlideApp.with(CoreApplication.getGlobalContext()).load(item.companyDetail.companyLogo).into((ImageView) helper.itemView.findViewById(R.id.iv_job_company_logo));
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListEvent event = ListEvent.newEvent(ListEvent.HOME_JOB_LIST_CLICK);
                event.eventBody = item.jobID;
                EventBus.getDefault().post(event);
            }
        });
        if (helper.getAdapterPosition() == getData().size()) {
            LogUtil.debugLog("触发加载更多:" + helper.getAdapterPosition());
            EventBus.getDefault().post(ListEvent.newEvent(ListEvent.HOME_JOB_LIST_LOADMORE));
        }
    }

    public static String getCompanySize(int careerCount) {
        String companySize = "10000人以上";
        if (careerCount < 20) {
            companySize = "20人以下";
        } else if (20 <= careerCount && careerCount < 100) {
            companySize = "20-100人";
        } else if (100 <= careerCount && careerCount < 500) {
            companySize = "100-500人";
        } else if (500 <= careerCount && careerCount < 2000) {
            companySize = "500-2000人";
        } else if (2000 <= careerCount && careerCount < 5000) {
            companySize = "2000-5000人";
        } else if (5000 <= careerCount && careerCount < 10000) {
            companySize = "5000-10000人";
        }
        return companySize;
    }
}
