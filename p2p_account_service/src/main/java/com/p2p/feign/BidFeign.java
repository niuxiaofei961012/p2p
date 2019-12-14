package com.p2p.feign;

import com.p2p.entity.BidRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("bid-service")
public interface BidFeign {
    @PostMapping("bid/addBidRecord")
    boolean addBidRecord(@RequestBody BidRecord bidRecord);
}
