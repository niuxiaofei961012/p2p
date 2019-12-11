package com.p2p.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util {
    public static String getMd5(String password){
        return DigestUtils.md5Hex(password);
    }

    public static void main(String[] args) {
        String admin = Md5Util.getMd5("admin");
        System.out.println(admin);
    }
}


