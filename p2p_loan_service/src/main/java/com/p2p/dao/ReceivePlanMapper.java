package com.p2p.dao;

import com.p2p.dto.ReceivePlanDTO;
import com.p2p.entity.ReceivePlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReceivePlanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReceivePlan record);

    int insertSelective(ReceivePlan record);

    ReceivePlan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReceivePlan record);

    int updateByPrimaryKey(ReceivePlan record);

    List<ReceivePlan> selectReceiveLists(@Param("receivePlanDTO") ReceivePlanDTO receivePlanDTO);
}