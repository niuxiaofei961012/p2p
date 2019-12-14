package com.p2p.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class AccountFlow {
    private Integer recordId;
    //动账时间
    private Date recordDate;
    //操作类型
    private String recordHandletype;
    //操作金额
    private BigDecimal recordHandlemoney;
    //账户id
    private Integer accountId;
    //结余
    private BigDecimal recordSurplus;
    //备注
    private String recordNotes;


}