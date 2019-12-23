package com.p2p.controller;

import com.github.pagehelper.PageInfo;
import com.p2p.dto.ReceivePlanDTO;
import com.p2p.entity.ReceivePlan;
import com.p2p.service.ReceiveService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("receive")
public class ReceiveController {
    @Resource
    private ReceiveService receiveService;

    /**
     * 回款记录查询+分页+展示
     * @param receivePlanDTO
     * @return
     */
    @PostMapping("selectReceiveLists")
    public PageInfo<ReceivePlan> selectReceiveLists(@RequestBody ReceivePlanDTO receivePlanDTO){
        return receiveService.selectReceiveLists(receivePlanDTO);
    }
}
