package com.p2p.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util {
    public static String getMd5(String password){
        return DigestUtils.md5Hex(password);
    }
}


