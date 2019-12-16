package com.p2p.service;

import com.github.pagehelper.PageInfo;
import com.p2p.dto.BidDTO;
import com.p2p.dto.UpdateAccountFrobalanceDTO;
import com.p2p.entity.Account;
import com.p2p.entity.AccountFlow;
import com.p2p.entity.RechargeRecord;
import org.springframework.data.domain.Page;

public interface AccountService {
    Account selectByPrimaryKey(Integer id);

    boolean verifyPassword(Integer id, String tradePassword);

    boolean bid(BidDTO bidDTO);

    boolean recharge(RechargeRecord rechargeRecord);

    PageInfo<RechargeRecord> getRechargeRecordList(RechargeRecord rechargeRecord, Integer pageNo, Integer pageSize);

    int subtractFrobalanceAndAddAvbalance(UpdateAccountFrobalanceDTO updateAccountFrobalanceDTO);

    int insert(AccountFlow accountFlow);
}
