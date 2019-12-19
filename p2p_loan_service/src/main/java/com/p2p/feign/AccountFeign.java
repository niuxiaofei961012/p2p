package com.p2p.feign;

import com.p2p.dto.UpdateAccountByBidUserIdDTO;
import com.p2p.entity.Account;
import com.p2p.entity.AccountFlow;
import com.p2p.vo.LoanMarkVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("account-service")
public interface AccountFeign {
    /**
     * 满标放款 修改借款人账户信息
     * @param loanMarkVO
     * @return
     */
    @PostMapping("p2p/account/updateAccountByBorrowUser")
    boolean updateAccountByBorrowUser(@RequestBody LoanMarkVO loanMarkVO);


    /**
     * 满标放款 修改投标人账户信息
     * @param updateAccountByBidUserIdDTO
     * @return
     */
    @PostMapping("p2p/account/updateAccountByBidUser")
    boolean updateAccountByBidUser(@RequestBody UpdateAccountByBidUserIdDTO updateAccountByBidUserIdDTO);

    /**
     * 根据id查询账户信息
     * @param id
     * @return
     */
    @GetMapping("p2p/account/selectByPrimaryKey")
    Account selectByPrimaryKey(@RequestParam("id") Integer id);

    /**
     * 添加动账记录
     * @param accountFlow
     * @return
     */
    @PostMapping("p2p/account/insertAccountFlow")
    int insertAccountFlow(@RequestBody AccountFlow accountFlow);

}
