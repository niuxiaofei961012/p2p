package com.p2p.entity;

import com.p2p.utils.BitStateUtils;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class Account {
    private Integer id;

    private BigDecimal accoubtAvbalance;

    private BigDecimal accoubtFrobalance;

    private BigDecimal unreceivePrincipal;

    private BigDecimal unreceiveInterest;

    private BigDecimal unreceiveMoney;

    private BigDecimal creditLimit;

    private BigDecimal remainCreditLimit;

    private String tradePassword;

    private BigDecimal bidMoney;

    private Integer bitState;

    public boolean getEmailFlag() {
        return BitStateUtils.ifState(this.bitState,BitStateUtils.EMAIL_STATE);
    }
    public boolean getPhoneFlag() {
        return BitStateUtils.ifState(this.bitState,BitStateUtils.PHONE_STATE);
    }
    public boolean getRealNameFlag() {
        return BitStateUtils.ifState(this.bitState,BitStateUtils.REAL_NAME_STATE);
    }
    public boolean getVipFlag() {
        return BitStateUtils.ifState(this.bitState,BitStateUtils.VIP_STATE);
    }
}