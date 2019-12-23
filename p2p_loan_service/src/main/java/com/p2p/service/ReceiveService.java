package com.p2p.service;

import com.github.pagehelper.PageInfo;
import com.p2p.dto.ReceivePlanDTO;
import com.p2p.entity.ReceivePlan;

public interface ReceiveService {
    PageInfo<ReceivePlan> selectReceiveLists(ReceivePlanDTO receivePlanDTO);
}
