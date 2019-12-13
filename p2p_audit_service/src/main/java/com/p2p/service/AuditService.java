package com.p2p.service;

import com.p2p.entity.BorrowAuditRecord;

public interface AuditService {
    int addAudit(BorrowAuditRecord borrowAuditRecord);
}
