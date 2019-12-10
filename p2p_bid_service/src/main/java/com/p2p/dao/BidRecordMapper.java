package com.p2p.dao;

import com.p2p.DTO.BidRecordDTO;
import com.p2p.entity.BidRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BidRecordMapper {
    int deleteByPrimaryKey(Integer bidId);

    int insert(BidRecord record);

    int insertSelective(BidRecord record);

    BidRecord selectByPrimaryKey(Integer bidId);

    int updateByPrimaryKeySelective(BidRecord record);

    int updateByPrimaryKey(BidRecord record);

    List<BidRecordDTO> getBidRecordList(BidRecordDTO bidRecordDTO);
}