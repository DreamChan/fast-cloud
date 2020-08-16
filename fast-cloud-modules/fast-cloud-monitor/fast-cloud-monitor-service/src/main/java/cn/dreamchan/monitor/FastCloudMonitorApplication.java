package cn.dreamchan.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 日志模块
 * @author DreamChan
 */
@EnableFeignClients(basePackages = "cn.dreamchan")
@SpringCloudApplication
public class FastCloudMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastCloudMonitorApplication.class, args);
    }

}
