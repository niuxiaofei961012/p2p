package com.p2p.controller;

import com.github.pagehelper.PageInfo;
import com.p2p.DTO.BidRecordDTO;
import com.p2p.entity.BidRecord;
import com.p2p.service.BidRecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("bid")
public class BidRecordController {
    @Resource
    private BidRecordService bidRecordService;

    /**
     * 投标记录表
     * @param bidRecordDTO
     * @return
     */
    @RequestMapping("list")
    public PageInfo<BidRecordDTO> getBidRecordList(@RequestBody BidRecordDTO bidRecordDTO){
        return bidRecordService.getBidRecordList(bidRecordDTO);
    }

    /**
     * 添加投标记录
     * @param bidRecord
     * @return
     */
    @PostMapping("addBidRecord")
    public boolean addBidRecord(@RequestBody BidRecord bidRecord){
        return bidRecordService.addBidRecord(bidRecord);
    }

    /**
     * 根据借款标id查询次标的记录
     * @param borrowId
     * @return
     */
    @RequestMapping("selectBidRecordByBorrowId")
    public List<BidRecord> selectBidRecordByBorrowId(@RequestParam("borrowId") Integer borrowId){
        return bidRecordService.selectBidRecordByBorrowId(borrowId);
    }
}
