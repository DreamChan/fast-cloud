package cn.dreamchan.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.time.Duration;

/**
 * minio 配置属性
 * @author DreamChan
 */
@RefreshScope
@Data
@ConfigurationProperties("spring.minio")
public class MinioConfigurationProperties {

    private String url = "https://play.min.io";

    private String accessKey = "Q3AM3UQ867SPQQA43P2F";

    private String secretKey = "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG";

    private boolean secure = false;

    private String bucket;

    private String metricName = "minio.storage";

    private Duration connectTimeout = Duration.ofSeconds(10);

    private Duration writeTimeout = Duration.ofSeconds(60);

    private Duration readTimeout = Duration.ofSeconds(10);

    private boolean checkBucket = true;

    private boolean createBucket = true;

}