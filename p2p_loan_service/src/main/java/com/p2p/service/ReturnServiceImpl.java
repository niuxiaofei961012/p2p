package com.p2p.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.p2p.dao.ReturnPlanMapper;
import com.p2p.dto.ReturnPlanDTO;
import com.p2p.entity.ReturnPlan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ReturnServiceImpl implements ReturnService{
    @Resource
    private ReturnPlanMapper returnPlanMapper;

    @Override
    public PageInfo<ReturnPlan> selectReturnLists(ReturnPlanDTO returnPlanDTO) {
        PageHelper.startPage(returnPlanDTO.getPageNo(),returnPlanDTO.getPageSize());
        return new PageInfo<>(returnPlanMapper.selectReturnLists(returnPlanDTO));
    }
}
