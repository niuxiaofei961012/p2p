package com.p2p.entity;

import lombok.Data;

import java.util.Date;

/**
 * p2p用户类
 */
@Data
public class User {
    private Long jwtId;
    //主键id
    private Integer id;
    //用户名
    private String userUsername;
    //用户密码
    private String userPassword;
    //用户手机号
    private String userPhone;
    //微博
    private String userMicroblog;
    //推荐人/备注
    private String userPresenter;
    //会员种类
    private Integer userVip;
    //用户状态
    private Integer userStatus;
    //用户邮箱
    private String userEmail;
    //注册时间
    private Date userRegTime;
    //用户地址
    private String userAddress;

    public User(Integer id, String userUsername, String userPassword, String userPhone, String userMicroblog, String userPresenter, Integer userVip, Integer userStatus, String userEmail, Date userRegTime, String userAddress) {
        this.id = id;
        this.userUsername = userUsername;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userMicroblog = userMicroblog;
        this.userPresenter = userPresenter;
        this.userVip = userVip;
        this.userStatus = userStatus;
        this.userEmail = userEmail;
        this.userRegTime = userRegTime;
        this.userAddress = userAddress;
    }

    public User() {
    }

    public User(Long jwtId, String userUsername) {
        this.jwtId = jwtId;
        this.userUsername = userUsername;
    }
}

