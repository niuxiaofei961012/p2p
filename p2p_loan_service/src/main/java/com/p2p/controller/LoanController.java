package com.p2p.controller;

import com.github.pagehelper.PageInfo;
import com.p2p.VO.LoanMarkVO;
import com.p2p.dto.ComputerMoney;
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
     * 展示借款标信息(类型为审核前的)
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
     * @param id
     * @param status
     * @return
     */
    @PostMapping("updateStatus")
    public boolean updateStatus(Integer id,Integer status){
        return loanService.updateStatus(id,status);
    }

    @GetMapping("getLoanMarkById")
    public LoanMarkVO getLoanMarkById(Integer borrowSignId){
        return loanService.getLoanMarkById(borrowSignId);
    }
}
