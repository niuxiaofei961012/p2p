package com.p2p.service;

import com.p2p.entity.Account;

public interface AccountService {
    Account selectByPrimaryKey(Integer id);

    boolean verifyPassword(Integer id, String tradePassword);
}
