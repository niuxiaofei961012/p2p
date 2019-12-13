package com.p2p.service;

import com.p2p.dao.BorrowAuditRecordMapper;
import com.p2p.entity.BorrowAuditRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class AuditServiceImpl implements AuditService{
    @Resource
    private BorrowAuditRecordMapper borrowAuditRecordMapper;
    @Override
    public int addAudit(BorrowAuditRecord borrowAuditRecord) {
        return borrowAuditRecordMapper.insertSelective(borrowAuditRecord);
    }
}
