package com.p2p.dao;

import com.p2p.entity.BorrowAuditRecord;

public interface BorrowAuditRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BorrowAuditRecord record);

    int insertSelective(BorrowAuditRecord record);

    BorrowAuditRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BorrowAuditRecord record);

    int updateByPrimaryKey(BorrowAuditRecord record);
}