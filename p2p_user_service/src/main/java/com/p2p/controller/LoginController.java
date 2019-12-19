package com.p2p.controller;

import com.p2p.common.UserConstant;
import com.p2p.entity.LoginVO;
import com.p2p.entity.User;
import com.p2p.service.LoginService;
import com.p2p.service.UserService;
import com.p2p.utils.CookieUtil;
import com.p2p.utils.RedisService;
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

    @Resource
    private UserService userService;
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

    /**
     * 单点登录验证
     * @param request
     * @return
     */
    @PostMapping("sso")
    public User sso(HttpServletRequest request){
        String cookieValue = CookieUtil.getCookieValue(request, UserConstant.USER_COOKIE_CODE);
        RedisService redisService = new RedisService();
        User user = redisService.get(cookieValue, User.class);
        return user;
    }

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    @RequestMapping("getUserInfoById")
    public User getUserInfoById(@RequestParam("id") Integer id){
        return loginService.getUserInfoById(id);
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @PostMapping("updateUser")
    public boolean updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

}
