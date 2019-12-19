package com.p2p.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.p2p.dao.AccountFlowMapper;
import com.p2p.dao.AccountMapper;
import com.p2p.dao.RechargeRecordMapper;
import com.p2p.dto.*;
import com.p2p.entity.*;
import com.p2p.feign.BidFeign;
import com.p2p.feign.LoanMarkFeign;
import com.p2p.utils.AccountFlowUtil;
import com.p2p.utils.Md5Util;
import com.p2p.utils.PayNumber;
import com.p2p.vo.LoanMarkVO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;

    @Resource
    private AccountFlowMapper accountFlowMapper;

    @Resource
    private RechargeRecordMapper rechargeRecordMapper;

    @Resource
    private BidFeign bidFeign;

    @Resource
    private LoanMarkFeign loanMarkFeign;

    @Override
    public Account selectByPrimaryKey(Integer id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean verifyPassword(Integer id, String tradePassword) {
        String password = Md5Util.getMd5(tradePassword);
        //验证交易密码
        Account account = accountMapper.verifyPassword(id, password);
        if (account != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean bid(BidDTO bidDTO) {
        Account account = bidDTO.getAccount();
        LoanMark loanMark = bidDTO.getLoanMark();
        try {
            //修改账户金额信息
            accountMapper.updateMoney(account.getId(), account.getBidMoney());

            //修改完之后查询账户可用金额
            Account afterAccount = accountMapper.selectByPrimaryKey(account.getId());
            if (afterAccount != null) {
                //生成动账记录
                AccountFlow accountFlow = AccountFlowUtil.getAccountFlow(afterAccount, "初步投标成功",account.getBidMoney());
                int insert = accountFlowMapper.insert(accountFlow);
                if (insert == 0) {
                    return false;
                }
            }

            //生成投标记录
            BidRecord bidRecord = new BidRecord();
            bidRecord.setBorrowId(loanMark.getBorrowSignId());
            bidRecord.setBorrowName(loanMark.getBorrowName());
            bidRecord.setBidMoney(account.getBidMoney());
            bidRecord.setYearRate(loanMark.getYearRate());
            bidRecord.setBidTime(loanMark.getPublishTime());
            bidRecord.setBidUserId(account.getId());
            bidRecord.setBorrowUserId(loanMark.getBorrowUserId());
            boolean addBidRecord = bidFeign.addBidRecord(bidRecord);
            if (!addBidRecord) {
                return false;
            }

            //修改借款标记录表募集到的金额
            LoanMarkDTO loanMarkDTO = new LoanMarkDTO();
            loanMarkDTO.setId(loanMark.getBorrowSignId());
            loanMarkDTO.setBidMoney(account.getBidMoney());
            boolean updateAccessMoney = loanMarkFeign.updateAccessMoney(loanMarkDTO);
            if (updateAccessMoney) {
                //修改成功之后查询募集到的金额是否满足满标情况
                LoanMarkVO loanMarkById = loanMarkFeign.getLoanMarkById(loanMark.getBorrowSignId());
                //判断借款金额是否等于募集到的金额
                System.out.println(loanMarkById);
                if (loanMarkById.getBorrowMoney().equals(loanMarkById.getAccessMoney())) {
                    //如果达到满标情况,修改借款标状态为满标
                    UpdateLoanMarkStatusTypeDTO updateLoanMarkStatusTypeDTO = new UpdateLoanMarkStatusTypeDTO();
                    updateLoanMarkStatusTypeDTO.setId(loanMark.getBorrowSignId());
                    updateLoanMarkStatusTypeDTO.setStatusType(1);
                    boolean updateStatusType = loanMarkFeign.updateStatusType(updateLoanMarkStatusTypeDTO);
                    if (!updateStatusType) {
                        return false;
                    }
                }
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean recharge(RechargeRecord rechargeRecord) {
        try {
            //充值记录表插入记录
            rechargeRecord.setDownOrderTime(new Date());
            rechargeRecord.setPayTime(new Date());
            rechargeRecord.setTradeCode(PayNumber.payNumber(new Date()));
            rechargeRecord.setPayStatus(0);
            rechargeRecord.setAuditStatue(0);
            int i = rechargeRecordMapper.insertSelective(rechargeRecord);
            if (i == 0) {
                return false;
            }
            //修改账户冻结金额
            int frobalance = accountMapper.updateAccoubtFrobalance(rechargeRecord.getCreateUserId(), rechargeRecord.getPayMoney());
            if (frobalance == 0) {
                return false;
            }
            //添加动账记录,先查询修改后用户的信息
            Account account = accountMapper.selectByPrimaryKey(rechargeRecord.getCreateUserId());
            //添加动账记录
            if (account != null) {
                //生成动账记录
                AccountFlow accountFlow = AccountFlowUtil.getAccountFlow(account, "初步充值成功", rechargeRecord.getPayMoney());
                int insert = accountFlowMapper.insert(accountFlow);
                if (insert == 0) {
                    return false;
                }
            }


            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PageInfo<RechargeRecord> getRechargeRecordList(RechargeRecord rechargeRecord, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(rechargeRecordMapper.getRechargeRecordList(rechargeRecord));
    }

    @Override
    public int subtractFrobalanceAndAddAvbalance(UpdateAccountFrobalanceDTO updateAccountFrobalanceDTO) {
        return accountMapper.subtractFrobalanceAndAddAvbalance(updateAccountFrobalanceDTO.getId(),updateAccountFrobalanceDTO.getRecharge());
    }

    @Override
    public int insert(AccountFlow accountFlow) {
        return accountFlowMapper.insert(accountFlow);
    }

    @Override
    public boolean updateAccountByBorrowUser(LoanMarkVO loanMarkVO) {
        //满标 借款人id,已经募集到的金额,所需还的本息,
        int i = accountMapper.updateAccountByBorrowUser(loanMarkVO.getBorrowUserId(), loanMarkVO.getAccessMoney(), loanMarkVO.getGrossInterest().add(loanMarkVO.getAccessMoney()));
        if (i>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAccountByBidUser(UpdateAccountByBidUserIdDTO updateAccountByBidUserIdDTO) {
        //满标 投标人id,收到的总利息,收到的本金(减少冻结资金)
        int i = accountMapper.updateAccountByBidUser(updateAccountByBidUserIdDTO.getAccountId(),updateAccountByBidUserIdDTO.getUnreceiveInterest(),updateAccountByBidUserIdDTO.getUnreceivePrincipal());
        if(i>0){
            return true;
        }
        return false;
    }

}
