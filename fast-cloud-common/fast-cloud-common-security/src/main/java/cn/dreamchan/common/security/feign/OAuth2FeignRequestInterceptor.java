package cn.dreamchan.common.security.feign;

import cn.dreamchan.common.core.constant.SecurityConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * feign 请求拦截器
 *
 * @author DreamChan
 */
@Component
public class OAuth2FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        if (authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
            OAuth2AuthenticationDetails dateils = (OAuth2AuthenticationDetails) authentication.getDetails();
            requestTemplate.header(HttpHeaders.AUTHORIZATION,
                    String.format("%s %s", SecurityConstants.BEARER_TOKEN_TYPE, dateils.getTokenValue()));
        }
    }

}