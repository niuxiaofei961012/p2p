package com.p2p.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateAccountByBidUserIdDTO {
    //账户id
    private Integer accountId;
    //待收本金
    private BigDecimal unreceivePrincipal;
    //待收本息
    private BigDecimal unreceiveInterest;
}
