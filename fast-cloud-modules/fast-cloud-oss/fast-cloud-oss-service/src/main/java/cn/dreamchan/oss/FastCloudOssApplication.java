package cn.dreamchan.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 对象存储 模块
 * @author DreamChan
 */
@EnableFeignClients(basePackages = "cn.dreamchan")
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FastCloudOssApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastCloudOssApplication.class, args);
    }

}
