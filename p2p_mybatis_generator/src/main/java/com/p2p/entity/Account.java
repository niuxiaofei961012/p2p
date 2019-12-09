package com.p2p.entity;

import java.math.BigDecimal;

public class Account {
    private Integer id;

    private BigDecimal accoubtAvbalance;

    private BigDecimal accoubtFrobalance;

    private BigDecimal unreceivePrincipal;

    private BigDecimal unreceiveInterest;

    private BigDecimal unreceiveMoney;

    private BigDecimal creditLimit;

    private BigDecimal remainCreditLimit;

    private String tradePassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAccoubtAvbalance() {
        return accoubtAvbalance;
    }

    public void setAccoubtAvbalance(BigDecimal accoubtAvbalance) {
        this.accoubtAvbalance = accoubtAvbalance;
    }

    public BigDecimal getAccoubtFrobalance() {
        return accoubtFrobalance;
    }

    public void setAccoubtFrobalance(BigDecimal accoubtFrobalance) {
        this.accoubtFrobalance = accoubtFrobalance;
    }

    public BigDecimal getUnreceivePrincipal() {
        return unreceivePrincipal;
    }

    public void setUnreceivePrincipal(BigDecimal unreceivePrincipal) {
        this.unreceivePrincipal = unreceivePrincipal;
    }

    public BigDecimal getUnreceiveInterest() {
        return unreceiveInterest;
    }

    public void setUnreceiveInterest(BigDecimal unreceiveInterest) {
        this.unreceiveInterest = unreceiveInterest;
    }

    public BigDecimal getUnreceiveMoney() {
        return unreceiveMoney;
    }

    public void setUnreceiveMoney(BigDecimal unreceiveMoney) {
        this.unreceiveMoney = unreceiveMoney;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getRemainCreditLimit() {
        return remainCreditLimit;
    }

    public void setRemainCreditLimit(BigDecimal remainCreditLimit) {
        this.remainCreditLimit = remainCreditLimit;
    }

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword == null ? null : tradePassword.trim();
    }
}