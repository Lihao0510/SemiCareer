package com.lihao.semicareer.entity;

/**
 * Created by lihao on 2017/8/17.
 */
public class NewsComment {

    public int commentID;
    public int newsID;
    public int commentUser;
    public String commentText;
    public String commentTime;
    public int commentAgree;
    public SysUser userMessage;

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getNewsID() {
        return newsID;
    }

    public void setNewsID(int newsID) {
        this.newsID = newsID;
    }

    public int getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(int commentUser) {
        this.commentUser = commentUser;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public int getCommentAgree() {
        return commentAgree;
    }

    public void setCommentAgree(int commentAgree) {
        this.commentAgree = commentAgree;
    }

    public SysUser getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(SysUser userMessage) {
        this.userMessage = userMessage;
    }

    @Override
    public String toString() {
        return "NewsComment{" +
                "commentID=" + commentID +
                ", newsID=" + newsID +
                ", commentUser=" + commentUser +
                ", commentText='" + commentText + '\'' +
                ", commentTime='" + commentTime + '\'' +
                ", commentAgree=" + commentAgree +
                ", userMessage=" + userMessage +
                '}';
    }
}
