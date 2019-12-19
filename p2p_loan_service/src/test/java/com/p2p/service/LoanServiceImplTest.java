package com.p2p.service;

import com.p2p.dto.ComputerMoney;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;


@SpringBootTest
@RunWith(SpringRunner.class)
public class LoanServiceImplTest {
    @Resource
    private LoanService loanService;

    @Test
    public void computerMoney() {
        ComputerMoney computerMoney = new ComputerMoney(new BigDecimal(10000),new BigDecimal(6),12,2);
        BigDecimal bigDecimal = loanService.computerMoney(computerMoney);
        System.out.println("需要还的利息是:=============="+bigDecimal);
    }
}