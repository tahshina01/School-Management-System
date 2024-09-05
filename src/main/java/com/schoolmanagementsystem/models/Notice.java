package com.schoolmanagementsystem.models;

import java.time.LocalDate;

public class Notice {
    private int noticeID;
    private String noticeTitle;
    private String noticeDesc;
    private LocalDate issueDate;

    public Notice() {

    }

    public Notice(String noticeTitle, String noticeDesc, LocalDate issueDate) {
        this.noticeTitle = noticeTitle;
        this.noticeDesc = noticeDesc;
        this.issueDate = issueDate;
    }

    public Notice(String noticeTitle, String noticeDesc) {
        this.noticeTitle = noticeTitle;
        this.noticeDesc = noticeDesc;
    }

    public int getNoticeID() {
        return noticeID;
    }

    public void setNoticeID(int noticeID) {
        this.noticeID = noticeID;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeDesc() {
        return noticeDesc;
    }

    public void setNoticeDesc(String noticeDesc) {
        this.noticeDesc = noticeDesc;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
}
