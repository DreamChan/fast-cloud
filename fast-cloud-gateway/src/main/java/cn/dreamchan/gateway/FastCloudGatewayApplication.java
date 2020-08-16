package cn.dreamchan.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 网关模块
 * @author DreamChan
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class FastCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastCloudGatewayApplication.class, args);
    }

}