package com.p2p.controller;

import com.p2p.entity.User;
import com.p2p.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("p2p/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @PostMapping("updateUserInfo")
    public boolean updateUserInfo(@RequestBody User user){
        return userService.updateUser(user);
    }
}
