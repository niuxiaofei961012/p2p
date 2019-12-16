package com.p2p.dao;

import com.p2p.entity.RechargeRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RechargeRecordMapper {
    int deleteByPrimaryKey(Integer payId);

    int insert(RechargeRecord record);

    int insertSelective(RechargeRecord record);

    RechargeRecord selectByPrimaryKey(Integer payId);

    int updateByPrimaryKeySelective(RechargeRecord record);

    int updateByPrimaryKey(RechargeRecord record);
}