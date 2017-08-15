package com.lihao.semicareer.entity;

/**
 * Created by lihao on 2017/8/15.
 */

public class CareerJob {

    public int jobID;
    public int companyID;
    public CareerCompany companyDetail;
    public int jobEdu;
    public int jobExp;
    public String jobCName;
    public String cityName;
    public String cityCode;
    public String districtName;
    public String districCode;
    public String jobAddress;
    public String jobDescribe;
    public String jobSalary;
    public String jobTime;
    public String jobAttract;
    public int jobStatus;
    public int jobType;

    public int getJobType() {
        return jobType;
    }

    public void setJobType(int jobType) {
        this.jobType = jobType;
    }

    public int getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(int jobStatus) {
        this.jobStatus = jobStatus;
    }

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public CareerCompany getCompanyDetail() {
        return companyDetail;
    }

    public void setCompanyDetail(CareerCompany companyDetail) {
        this.companyDetail = companyDetail;
    }

    public int getJobEdu() {
        return jobEdu;
    }

    public void setJobEdu(int jobEdu) {
        this.jobEdu = jobEdu;
    }

    public int getJobExp() {
        return jobExp;
    }

    public void setJobExp(int jobExp) {
        this.jobExp = jobExp;
    }

    public String getJobCName() {
        return jobCName;
    }

    public void setJobCName(String jobCName) {
        this.jobCName = jobCName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistricCode() {
        return districCode;
    }

    public void setDistricCode(String districCode) {
        this.districCode = districCode;
    }

    public String getJobAddress() {
        return jobAddress;
    }

    public void setJobAddress(String jobAddress) {
        this.jobAddress = jobAddress;
    }

    public String getJobDescribe() {
        return jobDescribe;
    }

    public void setJobDescribe(String jobDescribe) {
        this.jobDescribe = jobDescribe;
    }

    public String getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(String jobSalary) {
        this.jobSalary = jobSalary;
    }

    public String getJobTime() {
        return jobTime;
    }

    public void setJobTime(String jobTime) {
        this.jobTime = jobTime;
    }

    public String getJobAttract() {
        return jobAttract;
    }

    public void setJobAttract(String jobAttract) {
        this.jobAttract = jobAttract;
    }

    @Override
    public String toString() {
        return "CareerJob{" +
                "jobID=" + jobID +
                ", companyID=" + companyID +
                ", companyDetail=" + companyDetail +
                ", jobEdu=" + jobEdu +
                ", jobExp=" + jobExp +
                ", jobCName='" + jobCName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", districtName='" + districtName + '\'' +
                ", districCode='" + districCode + '\'' +
                ", jobAddress='" + jobAddress + '\'' +
                ", jobDescribe='" + jobDescribe + '\'' +
                ", jobSalary='" + jobSalary + '\'' +
                ", jobTime='" + jobTime + '\'' +
                ", jobAttract='" + jobAttract + '\'' +
                '}';
    }

}
