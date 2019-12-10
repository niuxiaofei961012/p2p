package com.p2p.controller;

import com.p2p.dto.ComputerMoney;
import com.p2p.service.LoanService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@RequestMapping("loan")
public class LoanController {
    @Resource
    private LoanService loanService;

    @PostMapping("computerMoney")
    public BigDecimal computerMoney(@RequestBody ComputerMoney computerMoney){
        return loanService.computerMoney(computerMoney);
    }
}
