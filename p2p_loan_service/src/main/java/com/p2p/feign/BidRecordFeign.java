package com.p2p.feign;

import com.p2p.entity.BidRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("bid-service")
public interface BidRecordFeign {
    /**
     * 根据借款标id查询次标的记录
     * @param borrowId
     * @return
     */
    @RequestMapping("bid/selectBidRecordByBorrowId")
    List<BidRecord> selectBidRecordByBorrowId(@RequestParam("borrowId") Integer borrowId);
}
