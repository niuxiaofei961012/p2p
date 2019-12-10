package com.p2p.service;

import com.p2p.constant.ComputerMoneyConstant;
import com.p2p.dto.ComputerMoney;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class LoanServiceImpl implements LoanService {
    @Override
    public BigDecimal computerMoney(ComputerMoney computerMoney) {
        //初始化金额
        BigDecimal returnInterest = BigDecimal.ZERO;
        BigDecimal monthRate = computerMoney.getYearRate().divide(new BigDecimal(100)).divide(new BigDecimal(12), 6, RoundingMode.HALF_UP);
        switch (computerMoney.getPaymentMethod()) {
            case ComputerMoneyConstant.RETURN_TYPE_MONTH_RATE://按月到期
                BigDecimal monthInterest = computerMoney.getBorrowMoney().multiply(monthRate);//月利息=本金*月利率
                returnInterest = monthInterest.multiply(new BigDecimal(computerMoney.getReturnMonthes())).setScale(ComputerMoneyConstant.SCALE_STORE);
                System.out.println("按月到期");
                break;

            case ComputerMoneyConstant.RETURN_TYPE_MONTH_PRINCIPAL://等额本金
                returnInterest = new BigDecimal(computerMoney.getReturnMonthes() + 1).multiply(computerMoney.getBorrowMoney())
                        .multiply(monthRate).divide(new BigDecimal(2));
                System.out.println("等额本金");
                break;

            case ComputerMoneyConstant.RETURN_TYPE_MONTH_INTEREST://等额本息
                BigDecimal tmp1 = computerMoney.getBorrowMoney().multiply(monthRate);
                BigDecimal tmp2 = monthRate.add(BigDecimal.ONE).pow(computerMoney.getReturnMonthes());
                BigDecimal tmp3 = tmp2.subtract(BigDecimal.ONE);
                BigDecimal monthAmount = tmp1.multiply(tmp2).divide(tmp3, 8, RoundingMode.HALF_UP);
                returnInterest = monthAmount.multiply(new BigDecimal(computerMoney.getReturnMonthes())).subtract(computerMoney.getBorrowMoney());
                System.out.println("等额本息");
                break;

        }

        return returnInterest;
    }

}
