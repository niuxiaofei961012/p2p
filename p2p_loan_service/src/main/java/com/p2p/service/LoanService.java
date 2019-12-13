package com.p2p.service;

import com.github.pagehelper.PageInfo;
import com.p2p.VO.LoanMarkVO;
import com.p2p.dto.ComputerMoney;
import com.p2p.dto.ReceiveBeforeBidAuditDTO;
import com.p2p.entity.LoanMark;

import java.math.BigDecimal;

public interface LoanService {
    BigDecimal computerMoney(ComputerMoney computerMoney);

    boolean addLoanMark(LoanMark loanMark);

    PageInfo<LoanMarkVO> getLoanMarkList(Integer statusType,Integer pageNo,Integer pageSize);

    boolean updateStatus(ReceiveBeforeBidAuditDTO receiveBeforeBidAuditDTO);

    PageInfo<LoanMarkVO> getLoanMarkListByStatus(Integer userId,Integer status, Integer pageNo, Integer pageSize);

    LoanMarkVO getLoanMarkById(Integer borrowSignId);
}
