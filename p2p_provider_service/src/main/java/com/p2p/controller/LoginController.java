package com.p2p.controller;

import com.p2p.entity.LoginVO;
import com.p2p.service.LoginService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(allowCredentials = "true")
@RequestMapping("p2p")
@RestController
public class LoginController {
    @Resource
    private LoginService loginService;

    /**
     * 登录
     * @param loginVO
     * @return
     */
    @RequestMapping("login")
    public String login(@RequestBody LoginVO loginVO, HttpServletRequest request, HttpServletResponse response){

        return loginService.login(loginVO,request,response);
    }

}
