package com.p2p.controller;

import com.github.pagehelper.PageInfo;
import com.p2p.dto.BidDTO;
import com.p2p.dto.UpdateAccountByBidUserIdDTO;
import com.p2p.dto.UpdateAccountFrobalanceDTO;
import com.p2p.dto.VerifyDTO;
import com.p2p.entity.Account;
import com.p2p.entity.AccountFlow;
import com.p2p.entity.RechargeRecord;
import com.p2p.service.AccountService;
import com.p2p.vo.LoanMarkVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
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
    @GetMapping("selectByPrimaryKey")
    public Account selectByPrimaryKey(@RequestParam("id") Integer id){
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

    /**
     * 投标
     * @param bidDTO
     * @return
     */
    @PostMapping("bid")
    public boolean bid(@RequestBody BidDTO bidDTO){
        return accountService.bid(bidDTO);
    }

    /**
     * 充值
     * @param rechargeRecord
     * @return
     */
    @PostMapping("recharge")
    public boolean recharge(@RequestBody RechargeRecord rechargeRecord){
        return accountService.recharge(rechargeRecord);
    }

    /**
     * 查询所有充值记录
     * @param rechargeRecord
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("getRechargeRecordList")
    public PageInfo<RechargeRecord> getRechargeRecordList(RechargeRecord rechargeRecord, @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "5") Integer pageSize){
        return accountService.getRechargeRecordList(rechargeRecord,pageNo,pageSize);
    }

    /**
     * 减少冻结金额,增加可用金额
     * @param updateAccountFrobalanceDTO
     * @return
     */
    @PostMapping("subtractFrobalanceAndAddAvbalance")
    public int subtractFrobalanceAndAddAvbalance(@RequestBody UpdateAccountFrobalanceDTO updateAccountFrobalanceDTO){
        return accountService.subtractFrobalanceAndAddAvbalance(updateAccountFrobalanceDTO);
    }

    /**
     * 添加动账记录
     * @param accountFlow
     * @return
     */
    @PostMapping("insertAccountFlow")
    public int insertAccountFlow(@RequestBody AccountFlow accountFlow){
        return accountService.insert(accountFlow);
    }

    /**
     * 满标放款 修改借款人账户信息
     * @param loanMarkVO
     * @return
     */
    @PostMapping("updateAccountByBorrowUser")
    public boolean updateAccountByBorrowUser(@RequestBody LoanMarkVO loanMarkVO){
       return  accountService.updateAccountByBorrowUser(loanMarkVO);
    }

    /**
     * 满标放款 修改投标人账户信息
     * @param updateAccountByBidUserIdDTO
     * @return
     */
    @PostMapping("updateAccountByBidUser")
    public boolean updateAccountByBidUser(@RequestBody UpdateAccountByBidUserIdDTO updateAccountByBidUserIdDTO){
       return  accountService.updateAccountByBidUser(updateAccountByBidUserIdDTO);
    }
}
