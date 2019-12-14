package com.p2p.controller;

import com.github.pagehelper.PageInfo;
import com.p2p.DTO.BidRecordDTO;
import com.p2p.entity.BidRecord;
import com.p2p.service.BidRecordService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("bid")
public class BidRecordController {
    @Resource
    private BidRecordService bidRecordService;

    /**
     *
     * @param bidRecordDTO
     * @return
     */
    @RequestMapping("list")
    public PageInfo<BidRecordDTO> getBidRecordList(@RequestBody BidRecordDTO bidRecordDTO){
        return bidRecordService.getBidRecordList(bidRecordDTO);
    }

    /**
     * 投标记录
     * @param bidRecord
     * @return
     */
    @PostMapping("addBidRecord")
    public boolean addBidRecord(@RequestBody BidRecord bidRecord){
        return bidRecordService.addBidRecord(bidRecord);
    }
}
