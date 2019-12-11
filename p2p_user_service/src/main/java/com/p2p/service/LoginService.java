package com.p2p.service;

import com.p2p.entity.LoginVO;
import com.p2p.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface LoginService {
    Map<String,Object> login(LoginVO loginVO, HttpServletRequest request, HttpServletResponse response);

    User register(User user);

    Map<String, Object> menegerLogin(LoginVO loginVO, HttpServletRequest request, HttpServletResponse response);
}
