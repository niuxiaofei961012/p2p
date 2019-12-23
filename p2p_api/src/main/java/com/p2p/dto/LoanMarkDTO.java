package com.p2p.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 更新借款标信息表收集到的募集资金
 */
@Data
public class LoanMarkDTO {
    private Integer id;
    private BigDecimal bidMoney;
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
    private Integer borrowDays;
    //募集到的资金金额
    private BigDecimal accessMoney;
    //发布时间
    private Date publishTime;
    //借款人姓名
    private String borrowUserName;
    //借款标状态
    private Integer status;
    //总利息
    private BigDecimal grossInterest;
    //审核类型
    private Integer statusType;
    //到期时间
    private Date endTime;
    //分页下标
    Integer pageNo;
    //分页数量
    Integer pageSize;
    //投标人id
    Integer bidUserId;
}
