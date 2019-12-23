package com.p2p.service;

import com.github.pagehelper.PageInfo;
import com.p2p.dto.ReturnPlanDTO;
import com.p2p.entity.ReturnPlan;

public interface ReturnService {

    PageInfo<ReturnPlan> selectReturnLists(ReturnPlanDTO returnPlanDTO);
}
