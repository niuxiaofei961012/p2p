package com.p2p.controller;

import com.p2p.entity.BorrowAuditRecord;
import com.p2p.entity.RechargeRecord;
import com.p2p.service.AuditService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("audit")
public class AuditController {
    @Resource
    private AuditService auditService;

    /**
     * 添加借款标审核记录
     * @param borrowAuditRecord
     * @return
     */
    @PostMapping("addAudit")
    public int addAudit(@RequestBody BorrowAuditRecord borrowAuditRecord){
        return auditService.addAudit(borrowAuditRecord);
    }

    /**
     * 充值审核
     * @param rechargeRecord
     * @return
     */
    @PostMapping("auditRechargeRecord")
    public boolean auditRechargeRecord(@RequestBody RechargeRecord rechargeRecord){
        return auditService.auditRechargeRecord(rechargeRecord);
    }
}
