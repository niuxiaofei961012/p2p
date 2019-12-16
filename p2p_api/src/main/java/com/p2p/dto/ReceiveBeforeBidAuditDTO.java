package com.p2p.dto;

import lombok.Data;

import java.util.Date;

/**
 * 更新发表前状态
 */
@Data
public class ReceiveBeforeBidAuditDTO {
    private Integer borrowSignId;
    private Integer statusType;
    private Integer status;
    private String auditComment;
    private Integer auditUserId;
    private Date auditTime;
}
