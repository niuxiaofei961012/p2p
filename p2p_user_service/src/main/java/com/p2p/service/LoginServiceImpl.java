package com.p2p.service;

import com.alibaba.fastjson.JSON;
import com.p2p.common.UserConstant;
import com.p2p.dao.AccountMapper;
import com.p2p.dao.LoginMapper;
import com.p2p.dao.UserMapper;
import com.p2p.entity.Account;
import com.p2p.entity.LoginVO;
import com.p2p.entity.User;
import com.p2p.utils.Md5Util;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private AccountMapper accountMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public Map<String,Object> login(LoginVO loginVO, HttpServletRequest request, HttpServletResponse response) {
        //登录验证
        //数据库根据用户名查询用户
        Map<String,Object> map = new HashMap<>();

        User login = userMapper.login(loginVO.getUserUsername());
        if (login == null) {
            map.put("userName","用户名不存在");
            return map;
        }
        if (!login.getUserPassword().equals(Md5Util.getMd5(loginVO.getUserPassword()))) {
            map.put("userPassword","密码错误");
            return map;
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
        //cookie.setDomain("p2p.com");
        cookie.setMaxAge(1800);
        cookie.setPath("/");
        //将cookie发送到浏览器
        response.addCookie(cookie);
        map.put("ok",loginJson);
        return map;
    }

    @Override
    public User register(User user) {
        //密码加密
        user.setUserPassword(Md5Util.getMd5(user.getUserPassword()));
        //添加用户
        userMapper.insert(user);
        //获取添加后的用户id
        Integer id = user.getId();
        if(id!=null){
            //定义账户对象,共用id
            Account account = new Account();
            account.setId(id);
            //添加账户
            accountMapper.insertSelective(account);
            //根据id查询用户对象
            return userMapper.selectByPrimaryKey(id);
        }
       return null;
    }
}
