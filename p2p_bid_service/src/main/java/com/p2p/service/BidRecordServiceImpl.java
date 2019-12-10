package com.p2p.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.p2p.DTO.BidRecordDTO;
import com.p2p.dao.BidRecordMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BidRecordServiceImpl implements BidRecordService {
    @Resource
    private BidRecordMapper bidRecordMapper;

    @Override
    public PageInfo<BidRecordDTO> getBidRecordList(BidRecordDTO bidRecordDTO) {
        //开始分页
        PageHelper.startPage(bidRecordDTO.getPageNo(),bidRecordDTO.getPageSize());

        return new PageInfo<>(bidRecordMapper.getBidRecordList(bidRecordDTO));
    }
}
