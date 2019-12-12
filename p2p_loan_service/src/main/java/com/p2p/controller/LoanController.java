package com.p2p.controller;

import com.github.pagehelper.PageInfo;
import com.p2p.dto.ComputerMoney;
import com.p2p.entity.LoanMark;
import com.p2p.service.LoanService;
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
     */
    @PostMapping("getLoanMarkList")
    public PageInfo<LoanMark> getLoanMarkList(){
        return loanService.getLoanMarkList();
    }

    @PostMapping("updateStatus")
    public boolean updateStatus(Integer id,Integer status){
        return loanService.updateStatus(id,status);
    }
}
