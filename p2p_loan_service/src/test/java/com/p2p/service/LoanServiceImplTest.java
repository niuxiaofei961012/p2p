package com.p2p.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.p2p.dto.ComputerMoney;
import com.p2p.entity.AccountFlow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;


@SpringBootTest
@RunWith(SpringRunner.class)
public class LoanServiceImplTest {
    @Resource
    private LoanService loanService;
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void computerMoney() {
        ComputerMoney computerMoney = new ComputerMoney(new BigDecimal(10000),new BigDecimal(6),12,2);
        BigDecimal bigDecimal = loanService.computerMoney(computerMoney);
        System.out.println("需要还的利息是:=============="+bigDecimal);
    }

    @Test
    public void sendAccountFlow(){
        AccountFlow accountFlow = new AccountFlow();
        accountFlow.setRecordNotes("111");
        accountFlow.setRecordHandletype("accountFlows");
        accountFlow.setRecordDate(new Date());
        amqpTemplate.convertAndSend("myQueue", SerializationUtils.serialize(accountFlow));
    }
}