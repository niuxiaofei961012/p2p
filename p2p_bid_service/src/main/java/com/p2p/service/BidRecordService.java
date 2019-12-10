package com.p2p.service;

import com.github.pagehelper.PageInfo;
import com.p2p.DTO.BidRecordDTO;

public interface BidRecordService {
    PageInfo<BidRecordDTO> getBidRecordList(BidRecordDTO bidRecordDTO);
}
