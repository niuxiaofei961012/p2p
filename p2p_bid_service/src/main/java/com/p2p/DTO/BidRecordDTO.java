package com.p2p.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BidRecordDTO {
    //主键id
    private Integer bidId;
    //借款标id
    private Integer borrowId;
    //借款标姓名
    private String borrowName;
    //投资额
    private BigDecimal bidMoney;
    //年利率
    private String yearRate;
    //投标时间
    private Date bidTime;
    //投标人id
    private Integer bidUserId;
    //借款人id
    private Integer borrowUserId;
    //开始时间(查询)
    private Date startTime;
    //结束时间(查询)
    private Date endTime;
    //分页下标
    private Integer pageNo;
    //每页数量
    private Integer pageSize;
}
