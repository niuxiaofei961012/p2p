package com.p2p.feign;

import com.p2p.entity.BorrowAuditRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "audit-service")
public interface AuditFeign {
    @PostMapping("/audit/addAudit")
    int addAudit(@RequestBody BorrowAuditRecord borrowAuditRecord);
}
