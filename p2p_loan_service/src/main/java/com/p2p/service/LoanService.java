package com.p2p.service;

import com.github.pagehelper.PageInfo;
import com.p2p.dto.*;
import com.p2p.entity.ReceivePlan;
import com.p2p.vo.LoanMarkVO;
import com.p2p.entity.LoanMark;

import java.math.BigDecimal;

public interface LoanService {
    BigDecimal computerMoney(ComputerMoney computerMoney);

    boolean addLoanMark(LoanMark loanMark);

    PageInfo<LoanMarkVO> getLoanMarkList(Integer statusType,Integer pageNo,Integer pageSize);

    boolean updateStatus(ReceiveBeforeBidAuditDTO receiveBeforeBidAuditDTO);

    PageInfo<LoanMarkVO> getLoanMarkListByStatus(Integer userId,Integer status, Integer pageNo, Integer pageSize);

    LoanMarkVO getLoanMarkById(Integer borrowSignId);

    boolean updateAccessMoney(LoanMarkDTO loanMarkDTO);

    boolean updateStatusType(UpdateLoanMarkStatusTypeDTO updateLoanMarkStatusTypeDTO);

    boolean fullBidUpdateStatus(ReceiveBeforeBidAuditDTO receiveBeforeBidAuditDTO);

    PageInfo<LoanMarkVO> getLoanMarkLists(LoanMarkDTO loanMarkDTO);

    PageInfo<LoanMarkVO> getLoanMarkListsByBidUser(LoanMarkDTO loanMarkDTO);


}
