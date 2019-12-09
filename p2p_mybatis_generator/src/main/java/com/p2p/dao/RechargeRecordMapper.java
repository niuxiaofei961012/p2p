package com.p2p.dao;

import com.p2p.entity.RechargeRecord;

public interface RechargeRecordMapper {
    int deleteByPrimaryKey(Integer payId);

    int insert(RechargeRecord record);

    int insertSelective(RechargeRecord record);

    RechargeRecord selectByPrimaryKey(Integer payId);

    int updateByPrimaryKeySelective(RechargeRecord record);

    int updateByPrimaryKey(RechargeRecord record);
}