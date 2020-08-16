package cn.dreamchan.gateway.filter;

import cn.dreamchan.common.core.biz.R;
import cn.dreamchan.gateway.service.CaptchaService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 验证码过滤器
 *
 * @author DreamChan
 */
@Component
public class ValidateCodeFilter extends AbstractGatewayFilterFactory<Object> {

    private final static String AUTH_URL = "/oauth/token";

    private static final String BASIC_ = "Basic ";

    private static final String CODE = "code";

    private static final String UUID = "uuid";

    private static final String GRANT_TYPE = "grant_type";

    private static final String REFRESH_TOKEN = "refresh_token";

    @Autowired
    private CaptchaService captchaService;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 非登录请求，不处理
            if (!StringUtils.containsIgnoreCase(request.getURI().getPath(), AUTH_URL)) {
                return chain.filter(exchange);
            }

            // 刷新token请求，不处理
            String grantType = request.getQueryParams().getFirst(GRANT_TYPE);
            if (StringUtils.containsIgnoreCase(request.getURI().getPath(), AUTH_URL) && StringUtils.containsIgnoreCase(grantType, REFRESH_TOKEN)) {
                return chain.filter(exchange);
            }

            // 消息头存在内容，且不存在验证码参数，不处理
            String header = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (StringUtils.isNotEmpty(header) && StringUtils.startsWith(header, BASIC_)
                    && !request.getQueryParams().containsKey(CODE) && !request.getQueryParams().containsKey(UUID)) {
                return chain.filter(exchange);
            }
            try {
                captchaService.checkCaptcha(request.getQueryParams().getFirst(CODE),
                        request.getQueryParams().getFirst(UUID));
            } catch (Exception e) {
                ServerHttpResponse response = exchange.getResponse();
                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                return exchange.getResponse().writeWith(
                        Mono.just(response.bufferFactory().wrap(JSON.toJSONBytes(R.failure(e.getMessage())))));
            }
            return chain.filter(exchange);
        };
    }
}
