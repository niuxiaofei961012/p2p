package com.p2p.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.p2p.constant.ComputerMoneyConstant;
import com.p2p.constant.LoanMarkConstant;
import com.p2p.dao.LoanMarkMapper;
import com.p2p.dao.ReceivePlanMapper;
import com.p2p.dao.ReturnPlanMapper;
import com.p2p.dto.*;
import com.p2p.entity.*;
import com.p2p.feign.AccountFeign;
import com.p2p.feign.AuditFeign;
import com.p2p.feign.BidRecordFeign;
import com.p2p.utils.AccountFlowUtil;
import com.p2p.vo.LoanMarkVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {
    @Resource
    private LoanMarkMapper loanMarkMapper;

    @Resource
    private AuditFeign auditFeign;

    @Resource
    private AccountFeign accountFeign;

    @Resource
    private BidRecordFeign bidRecordFeign;

    @Resource
    private ReturnPlanMapper returnPlanMapper;

    @Resource
    private ReceivePlanMapper receivePlanMapper;

    @Override
    public BigDecimal computerMoney(ComputerMoney computerMoney) {
        //初始化金额
        BigDecimal returnInterest = BigDecimal.ZERO;
        BigDecimal monthRate = computerMoney.getYearRate().divide(new BigDecimal(100), 6, RoundingMode.HALF_UP).divide(new BigDecimal(computerMoney.getReturnMonthes()), 6, RoundingMode.HALF_UP);
        switch (computerMoney.getPaymentMethod()) {
            case ComputerMoneyConstant.RETURN_TYPE_MONTH_RATE://按月到期
                BigDecimal monthInterest = computerMoney.getBorrowMoney().multiply(monthRate);//月利息=本金*月利率
                returnInterest = monthInterest.multiply(new BigDecimal(computerMoney.getReturnMonthes())).setScale(2);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PageInfo<LoanMarkVO> getLoanMarkList(Integer statusType, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
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
            //修改发标前状态
            loanMarkMapper.updateStatus(borrowAuditRecord.getBorrowId(), borrowAuditRecord.getAuditStatus());
            //修改完后审核记录表添加信息
            int i = auditFeign.addAudit(borrowAuditRecord);
            if (i > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PageInfo<LoanMarkVO> getLoanMarkListByStatus(Integer userId, Integer status, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return new PageInfo<>(loanMarkMapper.getLoanMarkListByStatus(userId, status));
    }

    @Override
    public LoanMarkVO getLoanMarkById(Integer borrowSignId) {
        return loanMarkMapper.selectByPrimaryKey(borrowSignId);
    }

    @Override
    public boolean updateAccessMoney(LoanMarkDTO loanMarkDTO) {
        try {
            //修改募集到的资金
            loanMarkMapper.updateAccessMoney(loanMarkDTO.getId(), loanMarkDTO.getBidMoney());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateStatusType(UpdateLoanMarkStatusTypeDTO updateLoanMarkStatusTypeDTO) {
        try {
            loanMarkMapper.updateStatusType(updateLoanMarkStatusTypeDTO.getId(), updateLoanMarkStatusTypeDTO.getStatusType());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean fullBidUpdateStatus(ReceiveBeforeBidAuditDTO receiveBeforeBidAuditDTO) {
        //添加满标审核记录
        BorrowAuditRecord borrowAuditRecord = new BorrowAuditRecord();
        borrowAuditRecord.setBorrowId(receiveBeforeBidAuditDTO.getBorrowSignId());
        borrowAuditRecord.setAuditUserId(receiveBeforeBidAuditDTO.getAuditUserId());
        borrowAuditRecord.setAuditType(receiveBeforeBidAuditDTO.getStatusType());
        borrowAuditRecord.setAuditStatus(receiveBeforeBidAuditDTO.getStatus());
        borrowAuditRecord.setAuditComment(receiveBeforeBidAuditDTO.getAuditComment());
        borrowAuditRecord.setAuditTime(new Date());
        int i = auditFeign.addAudit(borrowAuditRecord);
        if (i > 0) {
            //添加审核记录成功,修改借款标的状态为还款中
            int status = loanMarkMapper.updateStatus(receiveBeforeBidAuditDTO.getBorrowSignId(), 3);
            if (status > 0) {
                //修改状态成功,开始放款
                //根据借款标id查询借款标的信息
                LoanMarkVO loanMarkVO = loanMarkMapper.selectByPrimaryKey(receiveBeforeBidAuditDTO.getBorrowSignId());
                //获取期数
                Integer monthes = loanMarkVO.getReturnMonthes();
                //获取还款日期
                Date publishTime = loanMarkVO.getPublishTime();

                //修改借款人可用金额,待还本息,可用授信额度
                boolean b = accountFeign.updateAccountByBorrowUser(loanMarkVO);
                if (b) {
                    //修改完之后添加动账记录
                    //根据借款人id查询账户信息
                    Account loanAccount = accountFeign.selectByPrimaryKey(loanMarkVO.getBorrowUserId());
                    //得到动账信息
                    AccountFlow loanAccountFlow = AccountFlowUtil.getAccountFlow(loanAccount, "借款人满标放款后对账户的操作", loanAccount.getAccoubtAvbalance());
                    //添加动账信息
                    int flow = accountFeign.insertAccountFlow(loanAccountFlow);
                    if (flow > 0) {
                        //生成还款计划
                        //定义List 集合接收添加还款计划后生成的主键id
                        List<Integer> returnPlanIdList = new ArrayList<>();
                        for (int j = 0; j < monthes; j++) {
                            ReturnPlan returnPlan = new ReturnPlan();
                            returnPlan.setBorrowId(loanMarkVO.getBorrowSignId());
                            returnPlan.setLoanName(loanMarkVO.getBorrowName());
                            returnPlan.setPhase(j + 1);
                            returnPlan.setReturnUserId(loanMarkVO.getBorrowUserId());
                            returnPlan.setReturnMoney(loanMarkVO.getBorrowMoney().add(loanMarkVO.getGrossInterest()).divide(new BigDecimal(loanMarkVO.getReturnMonthes()),2, RoundingMode.HALF_UP));
                            returnPlan.setReturnPrincipal(loanMarkVO.getBorrowMoney().divide(new BigDecimal(loanMarkVO.getReturnMonthes()),2, RoundingMode.HALF_UP));
                            returnPlan.setReturnInterest(loanMarkVO.getGrossInterest().divide(new BigDecimal(loanMarkVO.getReturnMonthes()),2, RoundingMode.HALF_UP));
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(new Date());
                            calendar.add(Calendar.MONTH, j + 1);
                            Date time = calendar.getTime();
                            returnPlan.setReturnDeadline(time);
                            returnPlan.setReturnStatus(0);
                            returnPlanMapper.insertSelective(returnPlan);
                            returnPlanIdList.add(returnPlan.getId());
                        }

                        try {
                            //借款人动账记录添加成功,操作投标人账户
                            //查询出次标的所有投标人
                            List<BidRecord> bidRecordList = bidRecordFeign.selectBidRecordByBorrowId(loanMarkVO.getBorrowSignId());
                            //遍历list 添加回款记录


                            //遍历每个投资人信息
                            for (BidRecord bidRecord : bidRecordList) {
                                //修改投资人账户信息
                                UpdateAccountByBidUserIdDTO updateAccountByBidUserIdDTO = new UpdateAccountByBidUserIdDTO();
                                //投标人id
                                updateAccountByBidUserIdDTO.setAccountId(bidRecord.getBidUserId());
                                //个人收钱比例
                                BigDecimal ratio = bidRecord.getBidMoney().divide(loanMarkVO.getBorrowMoney(), 2,  RoundingMode.HALF_UP);
                                //需要收的本金
                                updateAccountByBidUserIdDTO.setUnreceivePrincipal(bidRecord.getBidMoney());
                                //需要收的利息
                                updateAccountByBidUserIdDTO.setUnreceiveInterest(loanMarkVO.getGrossInterest().multiply(ratio));
                                //修改投标人账户信息
                                boolean b1 = accountFeign.updateAccountByBidUser(updateAccountByBidUserIdDTO);
                                if (b1) {
                                    //如果修改成功,生成动账记录
                                    Account bidAccount = accountFeign.selectByPrimaryKey(bidRecord.getBidUserId());
                                    AccountFlow bidAccountFlow = AccountFlowUtil.getAccountFlow(bidAccount, "投标人满标放款后对账户的操作", bidAccount.getAccoubtAvbalance());
                                    int i1 = accountFeign.insertAccountFlow(bidAccountFlow);
                                    //生成回款计划
                                    for (int j = 0; j < returnPlanIdList.size(); j++) {
                                        ReceivePlan receivePlan = new ReceivePlan();
                                        receivePlan.setReturnId(returnPlanIdList.get(j));
                                        receivePlan.setBorrowId(loanMarkVO.getBorrowSignId());
                                        receivePlan.setLoanName(loanMarkVO.getBorrowName());
                                        receivePlan.setPhase(j + 1);
                                        receivePlan.setReceiveMoney(bidRecord.getBidMoney().add(loanMarkVO.getGrossInterest().multiply(ratio)).divide(new BigDecimal(returnPlanIdList.size()), 2, RoundingMode.HALF_UP));
                                        receivePlan.setReceiveInterest(loanMarkVO.getYearRate() + "");
                                        receivePlan.setReceivePrincipal(bidRecord.getBidMoney().divide(new BigDecimal(returnPlanIdList.size()), 2, RoundingMode.HALF_UP));
                                        receivePlan.setInterestFee(loanMarkVO.getGrossInterest().multiply(ratio).divide(new BigDecimal(returnPlanIdList.size()), 2, RoundingMode.HALF_UP));
                                        receivePlan.setReceiveUserId(bidRecord.getBidUserId());
                                        Calendar calendar = Calendar.getInstance();
                                        calendar.setTime(new Date());
                                        calendar.add(Calendar.MONTH, j + 1);
                                        Date time = calendar.getTime();
                                        receivePlan.setReceiveDate(time);
                                        receivePlan.setReceiveStatus(0);
                                        receivePlanMapper.insertSelective(receivePlan);
                                    }
                                }
                            }
                            return true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                }
            }
        }
        return false;
    }

}
