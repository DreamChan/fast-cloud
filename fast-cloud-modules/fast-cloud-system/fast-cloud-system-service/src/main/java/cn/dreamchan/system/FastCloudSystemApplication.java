package cn.dreamchan.system;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 系统模块
 *
 * @author DreamChan
 */
@EnableFeignClients(basePackages = "cn.dreamchan")
@EnableTransactionManagement
@SpringCloudApplication
public class FastCloudSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastCloudSystemApplication.class, args);
    }

}
