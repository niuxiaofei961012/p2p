package com.p2p.service;

import com.p2p.dao.AccountMapper;
import com.p2p.entity.Account;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements AccountService{
    @Resource
    private AccountMapper accountMapper;
    @Override
    public Account selectByPrimaryKey(Integer id) {
        return accountMapper.selectByPrimaryKey(id);
    }
}
