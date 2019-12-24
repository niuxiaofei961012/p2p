package com.p2p.conf;

import com.p2p.utils.RsaUtils;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

@Data
@Component
public class JwtProperties {
    private String secret = "sc@Login(Auth}*^31)&czxy%"; // 密钥

    private String pubKeyPath = "D://rsa//rsa.pub";// 公钥

    private String priKeyPath = "D://rsa//rsa.pri";// 私钥

    private int expire = 30;// token过期时间

    private PublicKey publicKey; // 公钥

    private PrivateKey privateKey; // 私钥

    private static final Logger logger = LoggerFactory.getLogger(JwtProperties.class);

}
