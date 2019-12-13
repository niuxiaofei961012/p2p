package com.p2p.service;

import com.p2p.dao.AccountMapper;
import com.p2p.entity.Account;
import com.p2p.utils.Md5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
    @Resource
    private AccountMapper accountMapper;
    @Override
    public Account selectByPrimaryKey(Integer id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean verifyPassword(Integer id, String tradePassword) {
        String password = Md5Util.getMd5(tradePassword);
        Account account = accountMapper.verifyPassword(id,password);
        if(account!=null){
            return true;
        }
        return false;
    }
}
