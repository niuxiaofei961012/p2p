package com.p2p.service;

import com.p2p.entity.BorrowAuditRecord;
import com.p2p.entity.RechargeRecord;

public interface AuditService {
    int addAudit(BorrowAuditRecord borrowAuditRecord);

    boolean auditRechargeRecord(RechargeRecord rechargeRecord);
}
