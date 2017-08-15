package com.lihao.semicareer.entity;

/**
 * Created by lihao on 2017/8/15.
 */

public class CareerCompany {

    public int companyID;
    public String companyName;
    public String companyDesc;
    public String companyAddress;
    public String companyOwner;
    public String companyPhone;
    public String companyIntro;
    public String companyWebsite;
    public String companyLogo;
    public int companyType;
    public int companyTag;
    public int companyCareer;


    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
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

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyOwner() {
        return companyOwner;
    }

    public void setCompanyOwner(String companyOwner) {
        this.companyOwner = companyOwner;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyIntro() {
        return companyIntro;
    }

    public void setCompanyIntro(String companyIntro) {
        this.companyIntro = companyIntro;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public int getCompanyType() {
        return companyType;
    }

    public void setCompanyType(int companyType) {
        this.companyType = companyType;
    }

    public int getCompanyTag() {
        return companyTag;
    }

    public void setCompanyTag(int companyTag) {
        this.companyTag = companyTag;
    }

    public int getCompanyCareer() {
        return companyCareer;
    }

    public void setCompanyCareer(int companyCareer) {
        this.companyCareer = companyCareer;
    }

    @Override
    public String toString() {
        return "CareerCompany{" +
                "companyID=" + companyID +
                ", companyName='" + companyName + '\'' +
                ", companyDesc='" + companyDesc + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", companyOwner='" + companyOwner + '\'' +
                ", companyPhone='" + companyPhone + '\'' +
                ", companyIntro='" + companyIntro + '\'' +
                ", companyWebsite='" + companyWebsite + '\'' +
                ", companyLogo='" + companyLogo + '\'' +
                ", companyType=" + companyType +
                ", companyTag=" + companyTag +
                ", companyCareer=" + companyCareer +
                '}';
    }

}
