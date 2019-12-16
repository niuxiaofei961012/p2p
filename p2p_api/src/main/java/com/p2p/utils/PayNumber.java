package com.p2p.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PayNumber {
    synchronized public static String payNumber(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHMMSS");
        return simpleDateFormat.format(date);
    }
}
