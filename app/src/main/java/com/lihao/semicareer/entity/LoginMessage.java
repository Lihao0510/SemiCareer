package com.lihao.semicareer.entity;

/**
 * Created by lihao on 2017/8/18.
 */

public class LoginMessage {

    public int userID;
    public int userSex;
    public String userName;
    public String userCName;
    public String userPhone;
    public String userEmail;
    public String userSign;
    public String userHead;
    public String intentCity;
    public String intentBusiness;
    public String intentJob;
    public String createTime;

    public int lookNum;
    public int interviewNum;
    public int messageNum;

    public String userToken;
    public String userSecondarySec;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public String getUserCName() {
        return userCName;
    }

    public void setUserCName(String userCName) {
        this.userCName = userCName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getIntentCity() {
        return intentCity;
    }

    public void setIntentCity(String intentCity) {
        this.intentCity = intentCity;
    }

    public String getIntentBusiness() {
        return intentBusiness;
    }

    public void setIntentBusiness(String intentBusiness) {
        this.intentBusiness = intentBusiness;
    }

    public String getIntentJob() {
        return intentJob;
    }

    public void setIntentJob(String intentJob) {
        this.intentJob = intentJob;
    }

    public int getLookNum() {
        return lookNum;
    }

    public void setLookNum(int lookNum) {
        this.lookNum = lookNum;
    }

    public int getInterviewNum() {
        return interviewNum;
    }

    public void setInterviewNum(int interviewNum) {
        this.interviewNum = interviewNum;
    }

    public int getMessageNum() {
        return messageNum;
    }

    public void setMessageNum(int messageNum) {
        this.messageNum = messageNum;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getUserSecondarySec() {
        return userSecondarySec;
    }

    public void setUserSecondarySec(String userSecondarySec) {
        this.userSecondarySec = userSecondarySec;
    }

    @Override
    public String toString() {
        return "LoginMessage{" +
                "userID=" + userID +
                ", userSex=" + userSex +
                ", userCName='" + userCName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userSign='" + userSign + '\'' +
                ", userHead='" + userHead + '\'' +
                ", intentCity='" + intentCity + '\'' +
                ", intentBusiness='" + intentBusiness + '\'' +
                ", intentJob='" + intentJob + '\'' +
                ", lookNum=" + lookNum +
                ", interviewNum=" + interviewNum +
                ", messageNum=" + messageNum +
                ", userToken='" + userToken + '\'' +
                ", userSecondarySec='" + userSecondarySec + '\'' +
                '}';
    }
}
