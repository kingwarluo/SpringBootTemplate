package com.kingwarluo.template;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class TemplateRestfulApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void generatePassword() {
        String password = "111111";
        //将uuid设置为密码盐值
        String salt = UUID.randomUUID().toString().replaceAll("-","");
        SimpleHash simpleHash = new SimpleHash("MD5", password, salt, 1024);
        System.out.println("salt:" + salt);
        System.out.println("simpleHash:" + simpleHash.toHex());
    }

}
