package com.p2p.constant;

import lombok.Data;

@Data
public class LoanMarkConstant {
    /**
     * 借款标状态
     */
    public static final int LOAN_WAIT_AUDIT = 0;//待审核
    public static final int LOAN_AUDITED = 1;//审核通过
    public static final int LOAN_UNAUDIT = 2;//审核不通过

    /**
     * 审核类型
     */
    public static final int BEFORE_BID = 0;//发标前审核
    public static final int AFTER_BID = 1;//满标审核

}
