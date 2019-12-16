package com.p2p.dto;

import com.p2p.entity.Account;
import com.p2p.entity.LoanMark;
import lombok.Data;

/**
 * 投标DTO
 */
@Data
public class BidDTO {
    private Account account;
    private LoanMark loanMark;
}
