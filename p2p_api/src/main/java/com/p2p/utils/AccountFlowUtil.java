package com.p2p.utils;

import com.p2p.entity.Account;
import com.p2p.entity.AccountFlow;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

public class AccountFlowUtil {
    @Autowired
    private static AmqpTemplate amqpTemplate;
    //添加动账记录
    public static AccountFlow getAccountFlow(Account account, String handleType, BigDecimal money) {
        AccountFlow accountFlow = new AccountFlow();
        accountFlow.setRecordDate(new Date());
        accountFlow.setAccountId(account.getId());
        accountFlow.setRecordHandlemoney(money);
        accountFlow.setRecordSurplus(account.getAccoubtAvbalance());
        accountFlow.setRecordHandletype(handleType);
        accountFlow.setRecordNotes("账户id为:" + account.getId() + "进行了" + handleType);
        amqpTemplate.convertAndSend("myQueue",accountFlow);
        return accountFlow;
    }
}
