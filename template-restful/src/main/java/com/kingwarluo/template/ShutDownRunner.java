package com.kingwarluo.template;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShutDownRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("注册优雅停机钩子");
        // register shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("Do something in Shutdown Hook")));
    }
}
