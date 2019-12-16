package com.p2p.feign;

import com.p2p.dto.UpdateAccountFrobalanceDTO;
import com.p2p.entity.Account;
import com.p2p.entity.AccountFlow;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("account-service")
public interface AccountFeign {
    @PostMapping("p2p/account/subtractFrobalanceAndAddAvbalance")
    int subtractFrobalanceAndAddAvbalance(@RequestBody UpdateAccountFrobalanceDTO updateAccountFrobalanceDTO);


    @GetMapping("p2p/account/selectByPrimaryKey")
    Account selectByPrimaryKey(@RequestParam(value = "id") Integer id);

    @PostMapping("p2p/account/insertAccountFlow")
    int insertAccountFlow(@RequestBody AccountFlow accountFlow);
}
