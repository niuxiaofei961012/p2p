package com.p2p.service;

import com.p2p.dao.UserMapper;
import com.p2p.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public boolean updateUser(User user) {
        int i = userMapper.updateByPrimaryKeySelective(user);
        if(i>0){
            return true;
        }
        return false;
    }
}
