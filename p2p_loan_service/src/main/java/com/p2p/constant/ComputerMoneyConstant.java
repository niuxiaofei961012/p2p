package com.p2p.constant;

import lombok.Data;

@Data
public class ComputerMoneyConstant {
    public static final int RETURN_TYPE_MONTH_INTEREST = 0;//等额本息
    public static final int RETURN_TYPE_MONTH_PRINCIPAL = 1;//等额本金
    public static final int RETURN_TYPE_MONTH_RATE = 2;//按月到期
    public static final int SCALE_STORE = 6;//保留几位小数

}
