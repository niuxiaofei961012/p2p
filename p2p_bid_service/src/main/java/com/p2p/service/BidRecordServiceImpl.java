package com.p2p.service;

import com.p2p.dao.BidRecordMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BidRecordServiceImpl implements BidRecordService {
    @Resource
    private BidRecordMapper bidRecordMapper;
}
