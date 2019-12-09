package com.p2p.controller;

import com.p2p.entity.Account;
import com.p2p.service.AccountService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("p2p/account")
public class AccountController {
    @Resource
    private AccountService accountService;

    /**
     * 根据id查询账户信息
     * @param id
     * @return
     */
    @RequestMapping("selectByPrimaryKey")
    public Account selectByPrimaryKey(Integer id){
        return accountService.selectByPrimaryKey(id);
    }
}
