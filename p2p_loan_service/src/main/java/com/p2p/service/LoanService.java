package com.p2p.service;

import com.p2p.dto.ComputerMoney;

import java.math.BigDecimal;

public interface LoanService {
    BigDecimal computerMoney(ComputerMoney computerMoney);
}
