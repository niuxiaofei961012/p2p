package com.p2p.dao;

import com.p2p.entity.ReceivePlan;

public interface ReceivePlanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReceivePlan record);

    int insertSelective(ReceivePlan record);

    ReceivePlan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReceivePlan record);

    int updateByPrimaryKey(ReceivePlan record);
}