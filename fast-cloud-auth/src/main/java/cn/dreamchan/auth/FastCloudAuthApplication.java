package cn.dreamchan.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 认证模块
 *
 * @author DreamChan
 */
@SpringCloudApplication
@EnableFeignClients(basePackages = "cn.dreamchan")
public class FastCloudAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastCloudAuthApplication.class, args);
    }

}
