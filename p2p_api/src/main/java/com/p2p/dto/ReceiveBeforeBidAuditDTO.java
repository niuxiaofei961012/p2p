package com.p2p.dto;

import lombok.Data;

import java.util.Date;

/**
 * 更新发表前状态
 */
@Data
public class ReceiveBeforeBidAuditDTO {
    //借款标id
    private Integer borrowSignId;
    //审核类型(0:发标前,1:满标)
    private Integer statusType;
    //审核状态
    private Integer status;
    //审核备注
    private String auditComment;
    //审核人id
    private Integer auditUserId;
    //审核时间
    private Date auditTime;
}
