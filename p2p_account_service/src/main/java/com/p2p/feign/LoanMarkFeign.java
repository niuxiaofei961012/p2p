package com.p2p.feign;

import com.p2p.dto.LoanMarkDTO;
import com.p2p.dto.UpdateLoanMarkStatusTypeDTO;
import com.p2p.vo.LoanMarkVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loan-service")
public interface LoanMarkFeign {
    @PostMapping("loan/updateAccessMoney")
    boolean updateAccessMoney(@RequestBody LoanMarkDTO loanMarkDTO);

    @GetMapping("loan/getLoanMarkById")
    LoanMarkVO getLoanMarkById(@RequestParam("borrowSignId") Integer borrowSignId);

    @PostMapping("loan/updateStatusType")
    boolean updateStatusType(@RequestBody UpdateLoanMarkStatusTypeDTO updateLoanMarkStatusTypeDTO);
}
