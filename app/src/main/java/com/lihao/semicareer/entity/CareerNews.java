package com.lihao.semicareer.entity;

import java.util.List;

/**
 * Created by lihao on 2017/8/17.
 */

public class CareerNews {

    public int newsID;
    public String newsTitle;
    public String newsPic;
    public String newsContent;
    public String newsAuthor;
    public int newsType;
    public int newsTag;
    public String newsDonate;
    public String newsTime;
    public List<NewsComment> newsComments;

    public List<NewsComment> getNewsComments() {
        return newsComments;
    }

    public void setNewsComments(List<NewsComment> newsComments) {
        this.newsComments = newsComments;
    }

    public int getNewsID() {
        return newsID;
    }

    public void setNewsID(int newsID) {
        this.newsID = newsID;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsPic() {
        return newsPic;
    }

    public void setNewsPic(String newsPic) {
        this.newsPic = newsPic;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsAuthor() {
        return newsAuthor;
    }

    public void setNewsAuthor(String newsAuthor) {
        this.newsAuthor = newsAuthor;
    }

    public int getNewsType() {
        return newsType;
    }

    public void setNewsType(int newsType) {
        this.newsType = newsType;
    }

    public int getNewsTag() {
        return newsTag;
    }

    public void setNewsTag(int newsTag) {
        this.newsTag = newsTag;
    }

    public String getNewsDonate() {
        return newsDonate;
    }

    public void setNewsDonate(String newsDonate) {
        this.newsDonate = newsDonate;
    }

    public String getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }

    @Override
    public String toString() {
        return "CareerNews{" +
                "newsID=" + newsID +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsPic='" + newsPic + '\'' +
                ", newsContent='" + newsContent + '\'' +
                ", newsAuthor='" + newsAuthor + '\'' +
                ", newsType=" + newsType +
                ", newsTag=" + newsTag +
                ", newsDonate='" + newsDonate + '\'' +
                ", newsTime='" + newsTime + '\'' +
                ", newsComments=" + newsComments +
                '}';
    }

}
