package com.p2p.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.p2p.VO.LoanMarkVO;
import com.p2p.constant.ComputerMoneyConstant;
import com.p2p.constant.LoanMarkConstant;
import com.p2p.dao.LoanMarkMapper;
import com.p2p.dto.ComputerMoney;
import com.p2p.dto.ReceiveBeforeBidAuditDTO;
import com.p2p.entity.BorrowAuditRecord;
import com.p2p.entity.LoanMark;
import com.p2p.feign.AuditFeign;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {
    @Resource
    private LoanMarkMapper loanMarkMapper;

    @Resource
    private AuditFeign auditFeign;

    @Override
    public BigDecimal computerMoney(ComputerMoney computerMoney) {
        //初始化金额
        BigDecimal returnInterest = BigDecimal.ZERO;
        BigDecimal monthRate = computerMoney.getYearRate().divide(new BigDecimal(100)).divide(new BigDecimal(12));
        switch (computerMoney.getPaymentMethod()) {
            case ComputerMoneyConstant.RETURN_TYPE_MONTH_RATE://按月到期
                BigDecimal monthInterest = computerMoney.getBorrowMoney().multiply(monthRate);//月利息=本金*月利率
                returnInterest = monthInterest.multiply(new BigDecimal(computerMoney.getReturnMonthes())).setScale(ComputerMoneyConstant.SCALE_STORE);
                break;

            case ComputerMoneyConstant.RETURN_TYPE_MONTH_PRINCIPAL://等额本金
                returnInterest = new BigDecimal(computerMoney.getReturnMonthes() + 1).multiply(computerMoney.getBorrowMoney())
                        .multiply(monthRate).divide(new BigDecimal(2)).setScale(2);
                break;

            case ComputerMoneyConstant.RETURN_TYPE_MONTH_INTEREST://等额本息
                BigDecimal tmp1 = computerMoney.getBorrowMoney().multiply(monthRate);
                BigDecimal tmp2 = monthRate.add(BigDecimal.ONE).pow(computerMoney.getReturnMonthes());
                BigDecimal tmp3 = tmp2.subtract(BigDecimal.ONE);
                BigDecimal monthAmount = tmp1.multiply(tmp2).divide(tmp3, 2, RoundingMode.HALF_UP);
                returnInterest = monthAmount.multiply(new BigDecimal(computerMoney.getReturnMonthes())).subtract(computerMoney.getBorrowMoney());
                break;

        }

        return returnInterest;
    }

    @Override
    public boolean addLoanMark(LoanMark loanMark) {
        try {
            //设置审核类型状态
            loanMark.setStatusType(LoanMarkConstant.BEFORE_BID);
            //设置借款标状态
            loanMark.setStatus(LoanMarkConstant.LOAN_WAIT_AUDIT);
            //设置时间
            loanMark.setPublishTime(new Date());
            System.out.println(loanMark);
            loanMarkMapper.insertSelective(loanMark);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PageInfo<LoanMarkVO> getLoanMarkList(Integer statusType,Integer pageNo,Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(loanMarkMapper.getLoanMarkList(statusType));
    }

    @Override
    public boolean updateStatus(ReceiveBeforeBidAuditDTO receiveBeforeBidAuditDTO) {
        BorrowAuditRecord borrowAuditRecord = new BorrowAuditRecord();

        borrowAuditRecord.setBorrowId(receiveBeforeBidAuditDTO.getBorrowSignId());
        borrowAuditRecord.setAuditUserId(receiveBeforeBidAuditDTO.getAuditUserId());
        borrowAuditRecord.setAuditType(receiveBeforeBidAuditDTO.getStatusType());
        borrowAuditRecord.setAuditStatus(receiveBeforeBidAuditDTO.getStatus());
        borrowAuditRecord.setAuditComment(receiveBeforeBidAuditDTO.getAuditComment());
        borrowAuditRecord.setAuditTime(new Date());

        try {
            loanMarkMapper.updateStatus(borrowAuditRecord.getBorrowId(),borrowAuditRecord.getAuditStatus());
            auditFeign.addAudit(borrowAuditRecord);
            return true;
        }catch (Exception e){

        }
        return false;
    }

    @Override
    public PageInfo<LoanMarkVO> getLoanMarkListByStatus(Integer userId,Integer status, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(loanMarkMapper.getLoanMarkListByStatus(userId,status));
    }

    @Override
    public LoanMarkVO getLoanMarkById(Integer borrowSignId) {
        return loanMarkMapper.selectByPrimaryKey(borrowSignId);
    }

}
