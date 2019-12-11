package com.p2p.service;

import com.p2p.dto.ComputerMoney;
import com.p2p.entity.LoanMark;

import java.math.BigDecimal;

public interface LoanService {
    BigDecimal computerMoney(ComputerMoney computerMoney);

    boolean addLoanMark(LoanMark loanMark);
}
