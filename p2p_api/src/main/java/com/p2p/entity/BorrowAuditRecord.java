package com.p2p.entity;

import lombok.Data;

import java.util.Date;
@Data
public class BorrowAuditRecord {
    //审核记录表id
    private Integer id;
    //借款人id
    private Integer borrowId;
    //审核类型
    private Integer auditType;
    //审核状态
    private Integer auditStatus;
    //审核备注
    private String auditComment;
    //审核人id
    private Integer auditUserId;
    //审核时间
    private Date auditTime;

}