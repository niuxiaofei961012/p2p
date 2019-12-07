package com.p2p.service;

import com.alibaba.fastjson.JSON;
import com.p2p.common.UserConstant;
import com.p2p.dao.LoginMapper;
import com.p2p.dao.UserMapper;
import com.p2p.entity.LoginVO;
import com.p2p.entity.User;
import com.p2p.utils.Md5Util;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public String login(LoginVO loginVO, HttpServletRequest request, HttpServletResponse response) {
        //登录验证
        //数据库根据用户名查询用户
        User login = userMapper.login(loginVO.getUserUsername());
        if (login == null) {
            return "用户名不存在";
        }
        if (!login.getUserPassword().equals(Md5Util.getMd5(loginVO.getUserPassword()))) {
            return "密码错误";
        }

        //随机生成token
        String token = UUID.randomUUID().toString();
        //将登陆的对象转成json串
        String loginJson = JSON.toJSONString(login);
        //将登陆的对象json串存到redis中
        stringRedisTemplate.opsForValue().set(token, loginJson, 30 * 60, TimeUnit.SECONDS);
        //创建cookie
        Cookie cookie = new Cookie(UserConstant.USER_COOKIE_CODE, token);
        //设置cookie属性
        cookie.setDomain("p2p.com");
        cookie.setMaxAge(1800);
        cookie.setPath("/");
        //将cookie发送到浏览器
        response.addCookie(cookie);
        return loginJson;
    }
}
