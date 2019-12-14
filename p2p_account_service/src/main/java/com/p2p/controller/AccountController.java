package com.p2p.controller;

import com.p2p.dto.BidDTO;
import com.p2p.dto.VerifyDTO;
import com.p2p.entity.Account;
import com.p2p.service.AccountService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 密码验证
     * @param verifyDTO
     * @return
     */
    @PostMapping("verifyPassword")
    public boolean verifyPassword(@RequestBody VerifyDTO verifyDTO){
        return accountService.verifyPassword(verifyDTO.getId(),verifyDTO.getTradePassword());
    }

    @PostMapping("bid")
    public boolean bid(@RequestBody BidDTO bidDTO){
        return accountService.bid(bidDTO);
    }
}
