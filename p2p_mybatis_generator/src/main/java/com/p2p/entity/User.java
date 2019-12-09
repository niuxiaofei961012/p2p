package com.p2p.entity;

import java.util.Date;

public class User {
    private Integer id;

    private String userUsername;

    private String userPassword;

    private String userPhone;

    private String userMicroblog;

    private String userPresenter;

    private Integer userVip;

    private Integer userStatus;

    private String userEmail;

    private Date userRegTime;

    private String userAddress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername == null ? null : userUsername.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserMicroblog() {
        return userMicroblog;
    }

    public void setUserMicroblog(String userMicroblog) {
        this.userMicroblog = userMicroblog == null ? null : userMicroblog.trim();
    }

    public String getUserPresenter() {
        return userPresenter;
    }

    public void setUserPresenter(String userPresenter) {
        this.userPresenter = userPresenter == null ? null : userPresenter.trim();
    }

    public Integer getUserVip() {
        return userVip;
    }

    public void setUserVip(Integer userVip) {
        this.userVip = userVip;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public Date getUserRegTime() {
        return userRegTime;
    }

    public void setUserRegTime(Date userRegTime) {
        this.userRegTime = userRegTime;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }
}