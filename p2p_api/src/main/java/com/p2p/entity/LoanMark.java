package com.p2p.entity;

import java.math.BigDecimal;
import java.util.Date;

public class LoanMark {
    private Integer borrowSignId;

    private String borrowName;

    private Integer borrowUserId;

    private BigDecimal borrowMoney;

    private String yearRate;

    private String returnMonthes;

    private String borrowUse;

    private String paymentMethod;

    private String borrowDays;

    private BigDecimal accessMoney;

    private Date publishTime;

    private String status;

    public Integer getBorrowSignId() {
        return borrowSignId;
    }

    public void setBorrowSignId(Integer borrowSignId) {
        this.borrowSignId = borrowSignId;
    }

    public String getBorrowName() {
        return borrowName;
    }

    public void setBorrowName(String borrowName) {
        this.borrowName = borrowName == null ? null : borrowName.trim();
    }

    public Integer getBorrowUserId() {
        return borrowUserId;
    }

    public void setBorrowUserId(Integer borrowUserId) {
        this.borrowUserId = borrowUserId;
    }

    public BigDecimal getBorrowMoney() {
        return borrowMoney;
    }

    public void setBorrowMoney(BigDecimal borrowMoney) {
        this.borrowMoney = borrowMoney;
    }

    public String getYearRate() {
        return yearRate;
    }

    public void setYearRate(String yearRate) {
        this.yearRate = yearRate == null ? null : yearRate.trim();
    }

    public String getReturnMonthes() {
        return returnMonthes;
    }

    public void setReturnMonthes(String returnMonthes) {
        this.returnMonthes = returnMonthes == null ? null : returnMonthes.trim();
    }

    public String getBorrowUse() {
        return borrowUse;
    }

    public void setBorrowUse(String borrowUse) {
        this.borrowUse = borrowUse == null ? null : borrowUse.trim();
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod == null ? null : paymentMethod.trim();
    }

    public String getBorrowDays() {
        return borrowDays;
    }

    public void setBorrowDays(String borrowDays) {
        this.borrowDays = borrowDays == null ? null : borrowDays.trim();
    }

    public BigDecimal getAccessMoney() {
        return accessMoney;
    }

    public void setAccessMoney(BigDecimal accessMoney) {
        this.accessMoney = accessMoney;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}