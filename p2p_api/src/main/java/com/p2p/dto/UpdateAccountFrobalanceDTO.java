package com.p2p.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 修改冻结金额和可用余额
 */
@Data
public class UpdateAccountFrobalanceDTO {
    private Integer id;
    private BigDecimal recharge;
}
