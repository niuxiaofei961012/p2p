package com.p2p.controller;

import com.p2p.entity.Account;
import com.p2p.entity.User;
import com.p2p.feign.UserFeign;
import com.p2p.pojo.PhoneForm;
import com.p2p.service.AccountService;
import com.p2p.utils.BitStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("p2p/auth")
public class AuthController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserFeign userFeign;
    /**
     * 发送验证码
     * @param phoneNum
     */
    @RequestMapping("sendPhone")
    public void sendPhone(String phoneNum){
        Random random = new Random();
        StringBuffer result= new StringBuffer();
        for (int i=0;i<6;i++){
            result.append(random.nextInt(10));
        }
        System.out.println(result);
        if(result!=null && result.toString()!=""){
            //随机验证码放入redis
            redisTemplate.opsForValue().set(phoneNum,result.toString(),60, TimeUnit.SECONDS);
        }
    }

    /**
     * 校验手机验证码
     * @param phoneForm
     * @return
     */
    @PostMapping("verifyPhone")
    public boolean verifyPhone(@RequestBody PhoneForm phoneForm){
        if(phoneForm.getPhone()!=null && phoneForm.getPhone()!=""){
            //和redis中的值比较
            String redisCode = redisTemplate.opsForValue().get(phoneForm.getPhone());
            if(redisCode.equals(phoneForm.getVerifyCode())){
                //将验证的手机号添加到用户表
                User user = new User();
                user.setId(phoneForm.getUserId());
                user.setUserPhone(phoneForm.getPhone());
                boolean b = userFeign.updateUserInfo(user);
                if(b){
                    //位运算添加手机认证状态
                    Account account = new Account();
                    account.setId(phoneForm.getUserId());
                    account.setBitState(BitStateUtils.addState(phoneForm.getBitState(), BitStateUtils.PHONE_STATE));
                    boolean bool = accountService.updateByPrimaryKeySelective(account);
                    if(bool){
                        return true;
                    }
                }

            }
        }
        return false;
    }
}
