package com.p2p.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ComputerMoney {
    //借款总额
    private BigDecimal borrowMoney;
    //年利率
    private BigDecimal yearRate;
    //期数
    private int returnMonthes;
    //还款类型
    private int paymentMethod;

    public ComputerMoney(BigDecimal borrowMoney, BigDecimal yearRate, int returnMonthes, int paymentMethod) {
        this.borrowMoney = borrowMoney;
        this.yearRate = yearRate;
        this.returnMonthes = returnMonthes;
        this.paymentMethod = paymentMethod;
    }

    public ComputerMoney() {

    }
}
