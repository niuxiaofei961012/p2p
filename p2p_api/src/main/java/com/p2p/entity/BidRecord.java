package com.p2p.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class BidRecord {
    private Integer bidId;
    //借款标id
    private Integer borrowId;
    //借款标名称
    private String borrowName;
    //投资金额
    private BigDecimal bidMoney;
    //年化利率
    private BigDecimal yearRate;
    //投标时间
    private Date bidTime;
    //投标人id
    private Integer bidUserId;
    //借款人id
    private Integer borrowUserId;


}