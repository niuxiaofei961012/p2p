package com.p2p.controller;

import com.github.pagehelper.PageInfo;
import com.p2p.dto.*;
import com.p2p.entity.ReceivePlan;
import com.p2p.vo.LoanMarkVO;
import com.p2p.entity.LoanMark;
import com.p2p.service.LoanService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@RequestMapping("loan")
public class LoanController {
    @Resource
    private LoanService loanService;

    /**
     * 计算利息
     * @param computerMoney
     * @return
     */
    @PostMapping("computerMoney")
    public BigDecimal computerMoney(@RequestBody ComputerMoney computerMoney) {
        return loanService.computerMoney(computerMoney);
    }

    /**
     * 添加借款标信息
     * @param loanMark
     * @return
     */
    @PostMapping("addLoanMark")
    public boolean addLoanMark(@RequestBody LoanMark loanMark){
        return loanService.addLoanMark(loanMark);
    }

    /**
     * 展示借款标信息
     * @param statusType
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("getLoanMarkList")
    public PageInfo<LoanMarkVO> getLoanMarkList(@RequestParam("statusType") Integer statusType, @Param("pageNo") @RequestParam(defaultValue = "1") Integer pageNo,
                                                @Param("pageSize") @RequestParam(defaultValue = "5")Integer pageSize){
        return loanService.getLoanMarkList(statusType,pageNo,pageSize);
    }

    /**
     * 展示借款标信息(审核通过的)
     * @param status
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("getLoanMarkListByStatus")
    public PageInfo<LoanMarkVO> getLoanMarkListByStatus(@RequestParam("status") Integer status,@RequestParam("userId") Integer userId, @Param("pageNo") @RequestParam(defaultValue = "1") Integer pageNo,
                                              @Param("pageSize") @RequestParam(defaultValue = "5")Integer pageSize){
        return loanService.getLoanMarkListByStatus(userId,status,pageNo,pageSize);
    }

    /**
     * 修改发标前状态
     * @param receiveBeforeBidAuditDTO
     * @return
     */
    @PostMapping("updateStatus")
    public boolean updateStatus(@RequestBody ReceiveBeforeBidAuditDTO receiveBeforeBidAuditDTO){
        return loanService.updateStatus(receiveBeforeBidAuditDTO);
    }

    /**
     * 修改满标状态
     * @param receiveBeforeBidAuditDTO
     * @return
     */
    @PostMapping("fullBidUpdateStatus")
    public boolean fullBidUpdateStatus(@RequestBody ReceiveBeforeBidAuditDTO receiveBeforeBidAuditDTO){
        return loanService.fullBidUpdateStatus(receiveBeforeBidAuditDTO);
    }

    /**
     * 根据id查询借款标表 account feign接口调用修改募集到的金额
     * @param borrowSignId
     * @return
     */
    @GetMapping("getLoanMarkById")
    public LoanMarkVO getLoanMarkById(Integer borrowSignId){
        return loanService.getLoanMarkById(borrowSignId);
    }

    /**
     * account feign接口调用修改募集到的金额
     * @param loanMarkDTO
     * @return
     */
    @PostMapping("updateAccessMoney")
    public boolean updateAccessMoney(@RequestBody LoanMarkDTO loanMarkDTO){
        return loanService.updateAccessMoney(loanMarkDTO);
    }

    /**
     * 修改状态为满标 account feign接口调用
     * @param updateLoanMarkStatusTypeDTO
     * @return
     */
    @PostMapping("updateStatusType")
    public boolean updateStatusType(@RequestBody UpdateLoanMarkStatusTypeDTO updateLoanMarkStatusTypeDTO){
        return loanService.updateStatusType(updateLoanMarkStatusTypeDTO);
    }


    /**
     * 展示个人借款信息+分页+查询
     * @param loanMarkDTO
     * @return
     */
    @PostMapping("getLoanMarkLists")
    public PageInfo<LoanMarkVO> getLoanMarkLists(@RequestBody LoanMarkDTO loanMarkDTO){
        return loanService.getLoanMarkLists(loanMarkDTO);
    }
    /**
     * 展示个人投标信息+分页+查询
     * @param loanMarkDTO
     * @return
     */
    @PostMapping("getLoanMarkListsByBidUser")
    public PageInfo<LoanMarkVO> getLoanMarkListsByBidUser(@RequestBody LoanMarkDTO loanMarkDTO){
        return loanService.getLoanMarkListsByBidUser(loanMarkDTO);
    }


}
