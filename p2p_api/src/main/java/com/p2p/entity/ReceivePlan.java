package com.p2p.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class ReceivePlan {
    private Integer id;
    //所属还款计划id
    private Integer returnId;
    //借款标id
    private Integer borrowId;
    //贷款名称
    private String loanName;
    //第几期
    private Integer phase;
    //收款总金额
    private BigDecimal receiveMoney;
    //收款利率
    private String receiveInterest;
    //收款本金
    private BigDecimal receivePrincipal;
    //利息管理费
    private BigDecimal interestFee;
    //回款人id
    private Integer receiveUserId;
    //回款日
    private Date receiveDate;
    //实际回款日
    private Date actualReceiveDate;
    //回款状态(0:未还款,1:已还款)
    private Integer receiveStatus;
}