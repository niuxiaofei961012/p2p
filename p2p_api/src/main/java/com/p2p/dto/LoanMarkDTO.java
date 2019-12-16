package com.p2p.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 更新借款标信息表收集到的募集资金
 */
@Data
public class LoanMarkDTO {
    private Integer id;
    private BigDecimal bidMoney;
}
