package com.lihao.semicareer.entity;

/**
 * Created by lihao on 2017/8/17.
 */

public class CareerResume {

    public int resumeID;
    public int userID;
    public String userSign;
    public String userHead;
    public String userAdvance;
    public String intentCity;
    public int intentBusiness;
    public String intentJob;
    public int careerYear;
    public int highistDegree;
    public String userDesc;
    public String userSchool;
    public String userProfessor;
    public String userBirth;
    public String userAttach;
    public int userSex;
    public SysUser userMessage;

    @Override
    public String toString() {
        return "CareerResume{" +
                "resumeID=" + resumeID +
                ", userID=" + userID +
                ", userSign='" + userSign + '\'' +
                ", userHead='" + userHead + '\'' +
                ", userAdvance='" + userAdvance + '\'' +
                ", intentCity='" + intentCity + '\'' +
                ", intentBusiness='" + intentBusiness + '\'' +
                ", intentJob='" + intentJob + '\'' +
                ", careerYear=" + careerYear +
                ", highistDegree=" + highistDegree +
                ", userDesc='" + userDesc + '\'' +
                ", userSchool='" + userSchool + '\'' +
                ", userProfessor='" + userProfessor + '\'' +
                ", userBirth='" + userBirth + '\'' +
                ", userAttach='" + userAttach + '\'' +
                ", userSex=" + userSex +
                ", userMessage=" + userMessage +
                '}';
    }

    public SysUser getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(SysUser userMessage) {
        this.userMessage = userMessage;
    }

    public int getResumeID() {
        return resumeID;
    }

    public void setResumeID(int resumeID) {
        this.resumeID = resumeID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public String getUserAdvance() {
        return userAdvance;
    }

    public void setUserAdvance(String userAdvance) {
        this.userAdvance = userAdvance;
    }

    public String getIntentCity() {
        return intentCity;
    }

    public void setIntentCity(String intentCity) {
        this.intentCity = intentCity;
    }

    public int getIntentBusiness() {
        return intentBusiness;
    }

    public void setIntentBusiness(int intentBusiness) {
        this.intentBusiness = intentBusiness;
    }

    public String getIntentJob() {
        return intentJob;
    }

    public void setIntentJob(String intentJob) {
        this.intentJob = intentJob;
    }

    public int getCareerYear() {
        return careerYear;
    }

    public void setCareerYear(int careerYear) {
        this.careerYear = careerYear;
    }

    public int getHighistDegree() {
        return highistDegree;
    }

    public void setHighistDegree(int highistDegree) {
        this.highistDegree = highistDegree;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public String getUserSchool() {
        return userSchool;
    }

    public void setUserSchool(String userSchool) {
        this.userSchool = userSchool;
    }

    public String getUserProfessor() {
        return userProfessor;
    }

    public void setUserProfessor(String userProfessor) {
        this.userProfessor = userProfessor;
    }

    public String getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(String userBirth) {
        this.userBirth = userBirth;
    }

    public String getUserAttach() {
        return userAttach;
    }

    public void setUserAttach(String userAttach) {
        this.userAttach = userAttach;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

}
