package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author ruoyi
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RuoYiApplication {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  云笔记启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "        .__                   .___                       __          \n" +
                "  ____ |  |   ____  __ __  __| _/          ____   _____/  |_  ____  \n" +
                "_/ ___\\|  |  /  _ \\|  |  \\/ __ |  ______  /    \\ /  _ \\   __\\/ __ \\ \n" +
                "\\  \\___|  |_(  <_> )  |  / /_/ | /_____/ |   |  (  <_> )  | \\  ___/ \n" +
                " \\___  >____/\\____/|____/\\____ |         |___|  /\\____/|__|  \\___  >\n" +
                "     \\/                       \\/              \\/                 \\/ ");
    }
}
