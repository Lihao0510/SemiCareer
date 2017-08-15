package com.lihao.semicareer.entity;

/**
 * Created by lihao on 2017/8/13.
 */

public class JobItem {

    public int jobID;
    public String jobName;
    public String jobSalary;
    public String jobDesc;
    public String jobTime;
    public String companyName;
    public String companyDesc;
    public String companyLogoUrl;

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(String jobSalary) {
        this.jobSalary = jobSalary;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getJobTime() {
        return jobTime;
    }

    public void setJobTime(String jobTime) {
        this.jobTime = jobTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }

    public String getCompanyLogoUrl() {
        return companyLogoUrl;
    }

    public void setCompanyLogoUrl(String companyLogoUrl) {
        this.companyLogoUrl = companyLogoUrl;
    }

    @Override
    public String toString() {
        return "JobItem{" +
                "jobID=" + jobID +
                ", jobName='" + jobName + '\'' +
                ", jobSalary='" + jobSalary + '\'' +
                ", jobDesc='" + jobDesc + '\'' +
                ", jobTime='" + jobTime + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyDesc='" + companyDesc + '\'' +
                ", companyLogoUrl='" + companyLogoUrl + '\'' +
                '}';
    }
}
