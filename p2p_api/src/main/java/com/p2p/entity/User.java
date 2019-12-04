package com.p2p.entity;

import lombok.Data;

/**
 * p2p用户类
 */
@Data
public class User {
    private String id;
    private String user_username;
    private String user_password;
    private String user_phone;
    private String user_microblog;
    private String user_presenter;
    private String user_vip;
    private String user_status;
    private String user_email;
    private String user_reg_time;
    private String user_address;

    public User(String id, String user_username, String user_password, String user_phone, String user_microblog, String user_presenter, String user_vip, String user_status, String user_email, String user_reg_time, String user_address) {
        this.id = id;
        this.user_username = user_username;
        this.user_password = user_password;
        this.user_phone = user_phone;
        this.user_microblog = user_microblog;
        this.user_presenter = user_presenter;
        this.user_vip = user_vip;
        this.user_status = user_status;
        this.user_email = user_email;
        this.user_reg_time = user_reg_time;
        this.user_address = user_address;
    }

    public User() {
    }
}
