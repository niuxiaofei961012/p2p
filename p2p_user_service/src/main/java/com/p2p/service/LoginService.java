package com.p2p.service;

import com.p2p.entity.LoginVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {
    String login(LoginVO loginVO, HttpServletRequest request, HttpServletResponse response);
}
