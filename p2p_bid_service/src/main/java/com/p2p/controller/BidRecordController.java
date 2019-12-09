package com.p2p.controller;

import com.p2p.service.BidRecordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("bid")
public class BidRecordController {
    @Resource
    private BidRecordService bidRecordService;
}
