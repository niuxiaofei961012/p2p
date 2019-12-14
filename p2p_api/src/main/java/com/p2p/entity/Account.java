package com.p2p.entity;

import lombok.Data;

import java.math.BigDecimal;
@Data
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

    private BigDecimal bidMoney;

}