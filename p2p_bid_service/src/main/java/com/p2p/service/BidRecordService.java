package com.p2p.service;

import com.github.pagehelper.PageInfo;
import com.p2p.DTO.BidRecordDTO;
import com.p2p.entity.BidRecord;

import java.util.List;

public interface BidRecordService {
    PageInfo<BidRecordDTO> getBidRecordList(BidRecordDTO bidRecordDTO);

    boolean addBidRecord(BidRecord bidRecord);

    List<BidRecord> selectBidRecordByBorrowId(Integer borrowId);
}
