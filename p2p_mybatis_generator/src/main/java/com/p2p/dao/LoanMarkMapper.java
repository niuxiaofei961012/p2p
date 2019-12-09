package com.p2p.dao;

import com.p2p.entity.LoanMark;

public interface LoanMarkMapper {
    int deleteByPrimaryKey(Integer borrowSignId);

    int insert(LoanMark record);

    int insertSelective(LoanMark record);

    LoanMark selectByPrimaryKey(Integer borrowSignId);

    int updateByPrimaryKeySelective(LoanMark record);

    int updateByPrimaryKey(LoanMark record);
}