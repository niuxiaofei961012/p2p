package com.p2p.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ReceivePlan {
    private Integer id;

    private Integer returnId;

    private Integer borrowId;

    private String loanName;

    private String phase;

    private BigDecimal receiveMoney;

    private String receiveInterest;

    private BigDecimal receivePrincipal;

    private BigDecimal interestFee;

    private Integer receiveUserId;

    private Date receiveDate;

    private Date actualReceiveDate;

    private String receiveStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReturnId() {
        return returnId;
    }

    public void setReturnId(Integer returnId) {
        this.returnId = returnId;
    }

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public String getLoanName() {
        return loanName;
    }

    public void setLoanName(String loanName) {
        this.loanName = loanName == null ? null : loanName.trim();
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase == null ? null : phase.trim();
    }

    public BigDecimal getReceiveMoney() {
        return receiveMoney;
    }

    public void setReceiveMoney(BigDecimal receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    public String getReceiveInterest() {
        return receiveInterest;
    }

    public void setReceiveInterest(String receiveInterest) {
        this.receiveInterest = receiveInterest == null ? null : receiveInterest.trim();
    }

    public BigDecimal getReceivePrincipal() {
        return receivePrincipal;
    }

    public void setReceivePrincipal(BigDecimal receivePrincipal) {
        this.receivePrincipal = receivePrincipal;
    }

    public BigDecimal getInterestFee() {
        return interestFee;
    }

    public void setInterestFee(BigDecimal interestFee) {
        this.interestFee = interestFee;
    }

    public Integer getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(Integer receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Date getActualReceiveDate() {
        return actualReceiveDate;
    }

    public void setActualReceiveDate(Date actualReceiveDate) {
        this.actualReceiveDate = actualReceiveDate;
    }

    public String getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(String receiveStatus) {
        this.receiveStatus = receiveStatus == null ? null : receiveStatus.trim();
    }
}