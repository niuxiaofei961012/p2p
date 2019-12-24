package com.p2p.service;

import com.p2p.conf.JwtProperties;
import com.p2p.entity.LoginVO;
import com.p2p.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.PrivateKey;
import java.util.Map;

public interface LoginService {
    Map<String,Object> login(LoginVO loginVO, HttpServletRequest request, HttpServletResponse response);

    User register(User user);

    Map<String, Object> menegerLogin(LoginVO loginVO, HttpServletRequest request, HttpServletResponse response);

    User getUserInfoById(Integer id);

    String loginJWT(LoginVO loginVO, PrivateKey privateKey);
}
