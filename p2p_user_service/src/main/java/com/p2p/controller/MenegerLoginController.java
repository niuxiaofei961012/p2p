package com.p2p.controller;

import com.p2p.entity.LoginVO;
import com.p2p.service.LoginService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("p2p")
public class MenegerLoginController {
    @Resource
    private LoginService loginService;
    /**
     * 登录
     * @param loginVO
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("menegerLogin")
    public Map<String,Object> login(@RequestBody LoginVO loginVO, HttpServletRequest request, HttpServletResponse response){
        return loginService.menegerLogin(loginVO,request,response);
    }
}
