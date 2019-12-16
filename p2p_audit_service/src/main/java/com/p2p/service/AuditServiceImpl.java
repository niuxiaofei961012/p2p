package com.p2p.service;

import com.p2p.dao.BorrowAuditRecordMapper;
import com.p2p.dao.RechargeRecordMapper;
import com.p2p.dto.UpdateAccountFrobalanceDTO;
import com.p2p.entity.Account;
import com.p2p.entity.AccountFlow;
import com.p2p.entity.BorrowAuditRecord;
import com.p2p.entity.RechargeRecord;
import com.p2p.feign.AccountFeign;
import com.p2p.utils.AccountFlowUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Transactional
public class AuditServiceImpl implements AuditService{
    @Resource
    private BorrowAuditRecordMapper borrowAuditRecordMapper;

    @Resource
    private RechargeRecordMapper rechargeRecordMapper;

    @Resource
    private AccountFeign accountFeign;

    @Override
    public int addAudit(BorrowAuditRecord borrowAuditRecord) {
        //添加借款审核记录
        return borrowAuditRecordMapper.insertSelective(borrowAuditRecord);
    }

    @Override
    public boolean auditRechargeRecord(RechargeRecord rechargeRecord) {
        rechargeRecord.setAuditTime(new Date());
        try {
            //修改充值审核信息
            int i = rechargeRecordMapper.updateByPrimaryKeySelective(rechargeRecord);
            if(i>0){
                //如果修改成功,更新账户表,将冻结金额转入余额
                UpdateAccountFrobalanceDTO updateAccountFrobalanceDTO = new UpdateAccountFrobalanceDTO();
                updateAccountFrobalanceDTO.setId(rechargeRecord.getCreateUserId());
                updateAccountFrobalanceDTO.setRecharge(rechargeRecord.getPayMoney());
                int frobalanceAndAddAvbalance = accountFeign.subtractFrobalanceAndAddAvbalance(updateAccountFrobalanceDTO);
                if(frobalanceAndAddAvbalance>0){
                    //添加动账记录,动账类型最终充值成功
                    //查询账户信息
                    Account account = accountFeign.selectByPrimaryKey(rechargeRecord.getCreateUserId());
                    AccountFlow accountFlow = AccountFlowUtil.getAccountFlow(account, "最终充值成功", account.getAccoubtAvbalance());
                    int flow = accountFeign.insertAccountFlow(accountFlow);
                    if(flow>0){
                        return true;
                    }
                }

            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
