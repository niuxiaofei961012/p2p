package com.p2p.utils;

import com.p2p.entity.Account;
import com.p2p.entity.AccountFlow;

import java.math.BigDecimal;
import java.util.Date;

public class AccountFlowUtil {
    //添加动账记录
    public static AccountFlow getAccountFlow(Account account, String handleType, BigDecimal money) {
        AccountFlow accountFlow = new AccountFlow();
        accountFlow.setRecordDate(new Date());
        accountFlow.setAccountId(account.getId());
        accountFlow.setRecordHandlemoney(money);
        accountFlow.setRecordSurplus(account.getAccoubtAvbalance());
        accountFlow.setRecordHandletype("初步" + handleType + "成功");
        accountFlow.setRecordNotes("账户id为:" + account.getId() + "进行了" + handleType);
        return accountFlow;
    }
}
