package com.p2p.pojo;

import lombok.Data;

@Data
public class PhoneForm {
    private String phone;
    private String verifyCode;
    private Integer bitState;
    private Integer userId;
}
