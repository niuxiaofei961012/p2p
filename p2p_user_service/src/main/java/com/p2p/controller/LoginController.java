package com.p2p.controller;

import com.p2p.common.UserConstant;
import com.p2p.conf.JwtProperties;
import com.p2p.constant.JwtConstans;
import com.p2p.entity.LoginVO;
import com.p2p.entity.User;
import com.p2p.service.LoginService;
import com.p2p.service.UserService;
import com.p2p.utils.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.applet.Main;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

@RequestMapping("p2p")
@RestController
public class LoginController {

    @Resource
    private JwtProperties prop;

    @Resource
    private LoginService loginService;

    @Resource
    private UserService userService;

    /**
     * 登录
     *
     * @param loginVO
     * @return
     */
    @RequestMapping("login")
    public Map<String, Object> login(@RequestBody LoginVO loginVO, HttpServletRequest request, HttpServletResponse response) {
        return loginService.login(loginVO, request, response);
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @PostMapping("register")
    public User register(@RequestBody User user) {
        return loginService.register(user);
    }

    /**
     * 单点登录验证
     *
     * @param request
     * @return
     */
    @PostMapping("sso")
    public User sso(HttpServletRequest request) {
        String cookieValue = CookieUtil.getCookieValue(request, UserConstant.USER_COOKIE_CODE);
        RedisService redisService = new RedisService();
        User user = redisService.get(cookieValue, User.class);
        return user;
    }

    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping("getUserInfoById")
    public User getUserInfoById(@RequestParam("id") Integer id) {
        return loginService.getUserInfoById(id);
    }

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    @PostMapping("updateUser")
    public boolean updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }


    //JWT+RSA

    /**
     * 登录授权功能
     *
     * @param loginVO
     * @return
     */
    @PostMapping("loginJWT")
    public ResponseEntity<Void> login(@RequestBody LoginVO loginVO,
                                      HttpServletResponse response, HttpServletRequest request)  throws Exception {


        // rsa.pub文件名随意，例如：rsa.pub、rsa.io、pub.opp、rsapub.tyrf、rsa.txt、、、、
        String pubKeyPath = "D:\\rsa\\rsa.pub";
        String priKeyPath = "D:\\rsa\\rsa.pri";
        /**************解密**************/
        PrivateKey privateKey = RsaUtils.getPrivateKey(priKeyPath);

//      生成token
        String token = loginService.loginJWT(loginVO, privateKey);

        try {
            RsaUtils.generateKey(pubKeyPath, priKeyPath, token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (StringUtils.isBlank(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        response.setHeader("token",token);
//        写入cookie token
      CookieUtils.setCookie(request, response, JwtConstans.COOKIE_NAME, token, false);
//      response.addCookie(new Cookie(JwtConstans.COOKIE_NAME,token));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 验证token信息，并更新token时效。
     *
     * @param token
     * @return
     */
    @GetMapping("verifyJWT")
    public ResponseEntity<User> verifyUser(@CookieValue(JwtConstans.COOKIE_NAME) String token,
                                           HttpServletRequest request,
                                           HttpServletResponse response) {
        String pubKeyPath = "D:\\rsa\\rsa.pub";
        String priKeyPath = "D:\\rsa\\rsa.pri";

        try {
            User userInfo = JwtUtils.getInfoFromToken(token, RsaUtils.getPublicKey(pubKeyPath));
            // 解析成功要重新生成token
            String newToken = JwtUtils.generateToken(userInfo, RsaUtils.getPrivateKey(priKeyPath),30);
            // 更新cookie中的token
            CookieUtils.setCookie(request, response, JwtConstans.COOKIE_NAME, newToken, false);

            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
