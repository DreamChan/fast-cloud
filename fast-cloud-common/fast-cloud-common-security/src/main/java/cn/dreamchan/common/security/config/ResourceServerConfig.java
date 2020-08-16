package cn.dreamchan.common.security.config;

import cn.dreamchan.common.security.component.PermitProps;
import cn.dreamchan.common.security.handle.RestAccessDeniedHandler;
import cn.dreamchan.common.security.handle.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 资源服务配置
 *
 * @author DreamChan
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private ResourceServerProperties resourceServerProperties;

    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;

    @Autowired
    private PermitProps permitProps;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(10000);
        httpRequestFactory.setReadTimeout(10000);

        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        return restTemplate;
    }

    @Bean
    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        UserAuthenticationConverter userTokenConverter = new CommonUserConverter();
        accessTokenConverter.setUserTokenConverter(userTokenConverter);
        remoteTokenServices.setCheckTokenEndpointUrl(resourceServerProperties.getTokenInfoUri());
        remoteTokenServices.setClientId(oAuth2ClientProperties.getClientId());
        remoteTokenServices.setClientSecret(oAuth2ClientProperties.getClientSecret());
        remoteTokenServices.setRestTemplate(restTemplate());
        remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
        return remoteTokenServices;
    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenServices(tokenServices());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        List<String> ignoreUrls = permitProps.getIgnoreUrls();
        String[] urls = ignoreUrls.toArray(new String[ignoreUrls.size()]);

        http.headers().frameOptions().disable();
        http.csrf().disable();
        http.exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint());
        http.exceptionHandling().accessDeniedHandler(new RestAccessDeniedHandler());
        http.authorizeRequests().antMatchers(urls).permitAll();
        http.authorizeRequests().requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll();
        http.authorizeRequests().anyRequest().authenticated();
    }
}
