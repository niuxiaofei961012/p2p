package com.p2p;

import com.p2p.entity.User;
import com.p2p.utils.JwtUtils;
import com.p2p.utils.RsaUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VerifyJwtTest {
    @Test
    public void verifyJwt(){
        String pubKeyPath = "D:\\rsa\\rsa.pub";
        String priKeyPath = "D:\\rsa\\rsa.pri";
        try {
            User userInfo = JwtUtils.getInfoFromToken("eyJhbGciOiJSUzI1NiJ9.eyJ1c2VybmFtZSI6IuW8oOS4iSIsImV4cCI6MTU3NzI0NTk2OX0.rrrPMAZ3wFmJ79uj4XV8Qp-q8DnNAv5xolU2Jvve2KptL81s2OQzfFa1A1hxXDFE8fmL3aGSjko5MUM-04sY2MtAJ8rLH_43x3ZhbMRkXkr54xAhpFqSp0HyMbkX7CJ-FpdZYEXz1bqvOXyKxFGqIYji-0dgkVLK_p1ti_HnJxzhSDqLJL40hwPfOy3kmOfxB3USdeWr9npPC1j7AJSQvANpCl2v0Fc3ars4rIasfmstY6yAgWPBEw-vL4Yyvj82pgEW7vLyKVOgdrVj4e-Z5qdFtogbYDvu5jDSSmOKhMsfjkxu0G04XGIkGnOosXCGpf7QAEolEPvBkQWAgoBwzA", RsaUtils.getPublicKey(pubKeyPath));
            // 解析成功要重新生成token
            String newToken = JwtUtils.generateToken(userInfo, RsaUtils.getPrivateKey(priKeyPath),300000);
            System.out.println(userInfo);
            System.out.println(newToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
