package cn.dreamchan.oss.config;

import cn.dreamchan.oss.exception.MinioException;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author DreamChan
 */
@Slf4j
@Configuration
@ConditionalOnClass(MinioClient.class)
@EnableConfigurationProperties(MinioConfigurationProperties.class)
public class MinioConfiguration {

    @Autowired
    private MinioConfigurationProperties minioConfigurationProperties;

    @Bean
    public MinioClient minioClient() throws InvalidEndpointException, InvalidPortException, IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException, InvalidResponseException, MinioException, XmlParserException {

        MinioClient minioClient;
        try {
            if (!configuredProxy()) {
                minioClient = new MinioClient(
                        minioConfigurationProperties.getUrl(),
                        minioConfigurationProperties.getAccessKey(),
                        minioConfigurationProperties.getSecretKey(),
                        minioConfigurationProperties.isSecure()
                );
            } else {
                minioClient = new MinioClient(
                        minioConfigurationProperties.getUrl(),
                        0,
                        minioConfigurationProperties.getAccessKey(),
                        minioConfigurationProperties.getSecretKey(),
                        null,
                        minioConfigurationProperties.isSecure(),
                        client()
                );
            }
            minioClient.setTimeout(
                    minioConfigurationProperties.getConnectTimeout().toMillis(),
                    minioConfigurationProperties.getWriteTimeout().toMillis(),
                    minioConfigurationProperties.getReadTimeout().toMillis()
            );
        } catch (InvalidEndpointException | InvalidPortException e) {
            log.error("Error while connecting to Minio", e);
            throw e;
        }

        if (minioConfigurationProperties.isCheckBucket()) {
            try {
                log.debug("Checking if bucket {} exists", minioConfigurationProperties.getBucket());
                boolean b = minioClient.bucketExists(minioConfigurationProperties.getBucket());
                if (!b) {
                    if (minioConfigurationProperties.isCreateBucket()) {
                        try {
                            minioClient.makeBucket(minioConfigurationProperties.getBucket());
                        } catch (RegionConflictException e) {
                            throw new MinioException("Cannot create bucket", e);
                        }
                    } else {
                        throw new InvalidBucketNameException(minioConfigurationProperties.getBucket(), "Bucket does not exists");
                    }
                }
            } catch (InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException | ErrorResponseException | InternalException | InvalidResponseException | MinioException | XmlParserException
                    e) {
                log.error("Error while checking bucket", e);
                throw e;
            }
        }

        return minioClient;
    }

    private boolean configuredProxy() {
        String httpHost = System.getProperty("http.proxyHost");
        String httpPort = System.getProperty("http.proxyPort");
        return httpHost != null && httpPort != null;
    }

    private OkHttpClient client() {
        String httpHost = System.getProperty("http.proxyHost");
        String httpPort = System.getProperty("http.proxyPort");

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (httpHost != null)
            builder.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(httpHost, Integer.parseInt(httpPort))));

        return builder.build();
    }

}