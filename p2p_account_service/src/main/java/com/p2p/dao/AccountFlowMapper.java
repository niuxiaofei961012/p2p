package com.p2p.dao;

import com.p2p.entity.AccountFlow;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountFlowMapper {
    int deleteByPrimaryKey(Integer recordId);

    int insert(AccountFlow record);

    int insertSelective(AccountFlow record);

    AccountFlow selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(AccountFlow record);

    int updateByPrimaryKey(AccountFlow record);
}