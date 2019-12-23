package com.p2p.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ReturnPlanDTO {
    private Integer id;
    //借款标id
    private Integer borrowId;
    //贷款名称
    private String loanName;
    //第几期
    private Integer phase;
    //还款人id
    private Integer returnUserId;
    //还款本息
    private BigDecimal returnMoney;
    //还款本金
    private BigDecimal returnPrincipal;
    //还款利息
    private BigDecimal returnInterest;
    //还款截止日期
    private Date returnDeadline;
    //还款时间
    private Date returnTime;
    //还款状态(0:未还款,1:已还款)
    private Integer returnStatus;
    //分页下标
    private Integer pageNo;
    //分页数
    private Integer pageSize;
}
