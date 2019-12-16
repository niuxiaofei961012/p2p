package com.p2p.service;

import com.p2p.dto.BidDTO;
import com.p2p.entity.Account;
import com.p2p.entity.RechargeRecord;

public interface AccountService {
    Account selectByPrimaryKey(Integer id);

    boolean verifyPassword(Integer id, String tradePassword);

    boolean bid(BidDTO bidDTO);

    boolean recharge(RechargeRecord rechargeRecord);
}
