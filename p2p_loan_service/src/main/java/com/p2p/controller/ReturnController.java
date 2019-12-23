package com.p2p.controller;

import com.github.pagehelper.PageInfo;
import com.p2p.dto.ReturnPlanDTO;
import com.p2p.entity.ReturnPlan;
import com.p2p.service.ReturnService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("return")
public class ReturnController {
    @Resource
    private ReturnService returnService;
    /**
     * 还款记录查询+分页+展示
     * @param returnPlanDTO
     * @return
     */
    @PostMapping("selectReturnLists")
    public PageInfo<ReturnPlan> selectReturnLists(@RequestBody ReturnPlanDTO returnPlanDTO){
        return returnService.selectReturnLists(returnPlanDTO);
    }
}
