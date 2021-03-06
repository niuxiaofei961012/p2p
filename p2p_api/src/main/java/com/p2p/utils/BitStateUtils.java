package com.p2p.utils;

public class BitStateUtils {
    public static final int EMAIL_STATE = 1 << 0;
    public static final int PHONE_STATE = 1 << 1;
    public static final int REAL_NAME_STATE = 1 << 2;
    public static final int VIP_STATE = 1 << 3;

    /**
     * 为某个状态增加指定状态
     * @param oldState 旧状态
     * @param state 新状态
     * @return
     */
    public static int addState(int oldState,int state){
        return oldState | state;
    }

    /**
     * 删除某个状态
     * @param oldState 旧状态
     * @param state 要删除的状态
     * @return
     */
    public static int delState(int oldState,int state){
        return oldState ^ state;
    }

    /**
     * 判断某个状态
     * @param oldState 旧状态
     * @param state 新状态
     * @return
     */
    public static boolean ifState(int oldState,int state){
        return (oldState & state ) >0;
    }
    public static void main(String[] args) {

//        System.out.println("添加状态"+BitStateUtils.addState(5, PHONE_STATE));
//
//        System.out.println("移除状态"+BitStateUtils.delState(7,EMAIL_STATE));
//
//        System.out.println("判断状态"+BitStateUtils.ifState(1,PHONE_STATE));]


//        Account account = new Account();
//        account.setBitState(1);
//        System.out.println(account.getRealNameFlag());
//        System.out.println(account.getEmailFlag());
//        System.out.println(account.getVipFlag());
//        System.out.println(account.getPhoneFlag());
    }

}
