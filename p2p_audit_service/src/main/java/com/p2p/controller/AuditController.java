package com.p2p.controller;

import com.p2p.entity.BorrowAuditRecord;
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
    @PostMapping("addAudit")
    public int addAudit(@RequestBody BorrowAuditRecord borrowAuditRecord){
        return auditService.addAudit(borrowAuditRecord);
    }
}
