package com.p2p.dao;

import com.p2p.entity.ReturnPlan;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReturnPlanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReturnPlan record);

    int insertSelective(ReturnPlan record);

    ReturnPlan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReturnPlan record);

    int updateByPrimaryKey(ReturnPlan record);
}