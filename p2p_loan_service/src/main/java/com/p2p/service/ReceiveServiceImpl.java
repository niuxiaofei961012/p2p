package com.p2p.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.p2p.dao.ReceivePlanMapper;
import com.p2p.dto.ReceivePlanDTO;
import com.p2p.entity.ReceivePlan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ReceiveServiceImpl implements ReceiveService{
    @Resource
    private ReceivePlanMapper receivePlanMapper;
    @Override
    public PageInfo<ReceivePlan> selectReceiveLists(ReceivePlanDTO receivePlanDTO) {
        PageHelper.startPage(receivePlanDTO.getPageNo(),receivePlanDTO.getPageSize());
        return new PageInfo<>(receivePlanMapper.selectReceiveLists(receivePlanDTO));
    }
}
