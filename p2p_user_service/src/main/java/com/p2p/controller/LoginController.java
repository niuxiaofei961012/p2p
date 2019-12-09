package com.p2p.controller;

import com.p2p.entity.LoginVO;
import com.p2p.entity.User;
import com.p2p.service.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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
    public Map<String,Object> login(@RequestBody LoginVO loginVO, HttpServletRequest request, HttpServletResponse response){
        return loginService.login(loginVO,request,response);
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("register")
    public User register(@RequestBody User user){
        return loginService.register(user);
    }

}
