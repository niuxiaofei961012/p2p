package com.p2p.entity;

import java.math.BigDecimal;
import java.util.Date;

public class LoanMark {
    //借款标ID
    private Integer borrowSignId;
    //借款标名称
    private String borrowName;
    //贷款人id
    private Integer borrowUserId;
    //贷款金额
    private BigDecimal borrowMoney;
    //年化利率
    private BigDecimal yearRate;
    //还款期数
    private Integer returnMonthes;
    //借款用途
    private String borrowUse;
    //还款方式(等额本息、等额本金、按月到期)
    private Integer paymentMethod;
    //招标天数
    private String borrowDays;
    //募集到的资金金额
    private BigDecimal accessMoney;
    //发布时间
    private Date publishTime;
    //借款标状态
    private Integer status;
    //状态类型
    private Integer statusType;

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

    public Integer getStatusType() {
        return statusType;
    }

    public void setStatusType(Integer statusType) {
        this.statusType = statusType;
    }
}