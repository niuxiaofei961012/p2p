package com.p2p.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReceiveBeforeBidAuditDTO {
    private Integer borrowSignId;
    private Integer statusType;
    private Integer status;
    private String auditComment;
    private Integer auditUserId;
    private Date auditTime;
}
