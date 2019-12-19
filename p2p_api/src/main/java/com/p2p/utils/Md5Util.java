package com.p2p.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Md5Util {
    public static String getMd5(String password){
        return DigestUtils.md5Hex(password);
    }

    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = simpleDateFormat.format(new Date());
        System.out.println(format);

        for (int i =0 ;i<3;i++){
            Date date = Md5Util.getDate(new Date());
            System.out.println(date);
        }
    }

    public static Date getDate(Date date){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, 1);
            Date time = calendar.getTime();
            return time;

    }
}


