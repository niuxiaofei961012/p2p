package com.p2p.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class RechargeRecord {
    //主键id
    private Integer payId;
    //支付金额
    private BigDecimal payMoney;
    //下单时间
    private Date downOrderTime;
    //支付时间
    private Date payTime;
    //充值方式(0:线下1:线上)
    private Integer chargeType;
    //交易编号
    private String tradeCode;
    //付款单备注
    private String payComment;
    //支付状态
    private Integer payStatus;
    //用户id
    private Integer createUserId;
    //审核人id
    private Integer auditUserId;
    //审核状态(0:未审核,1:审核通过)
    private Integer auditStatue;
    //审核批注
    private String auditComment;
    //审核时间
    private Date auditTime;

}