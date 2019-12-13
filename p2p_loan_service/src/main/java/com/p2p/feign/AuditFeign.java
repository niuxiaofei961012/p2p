package com.p2p.feign;

import com.p2p.entity.BorrowAuditRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "audit-service")
public interface AuditFeign {
    @RequestMapping("/audit/addAudit")
    int addAudit(@RequestBody BorrowAuditRecord borrowAuditRecord);
}
