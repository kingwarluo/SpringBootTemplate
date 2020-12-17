package com.kingwarluo.template;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kingwarluo.template.modules.*")
public class TemplateRestfulApplication {

    public static void main(String[] args) {
        SpringApplication.run(TemplateRestfulApplication.class, args);
    }

}
