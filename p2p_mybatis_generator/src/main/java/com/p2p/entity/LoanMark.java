package com.p2p.entity;

import java.math.BigDecimal;
import java.util.Date;

public class LoanMark {
    private Integer borrowSignId;

    private String borrowName;

    private Integer borrowUserId;

    private BigDecimal borrowMoney;

    private BigDecimal yearRate;

    private Integer returnMonthes;

    private String borrowUse;

    private Integer paymentMethod;

    private String borrowDays;

    private BigDecimal accessMoney;

    private Date publishTime;

    private Integer status;

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

    public BigDecimal getYearRate() {
        return yearRate;
    }

    public void setYearRate(BigDecimal yearRate) {
        this.yearRate = yearRate;
    }

    public Integer getReturnMonthes() {
        return returnMonthes;
    }

    public void setReturnMonthes(Integer returnMonthes) {
        this.returnMonthes = returnMonthes;
    }

    public String getBorrowUse() {
        return borrowUse;
    }

    public void setBorrowUse(String borrowUse) {
        this.borrowUse = borrowUse == null ? null : borrowUse.trim();
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}