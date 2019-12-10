package com.p2p.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("loan")
public class LoanController {

    @RequestMapping("computerMoney")
    public BigDecimal computerMoney(){

    }
}
