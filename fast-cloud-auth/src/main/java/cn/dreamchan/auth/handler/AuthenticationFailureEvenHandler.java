package cn.dreamchan.auth.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * 认证失败后的处理
 *
 * @author DreamChan
 */
@Slf4j
@Component
public class AuthenticationFailureEvenHandler implements ApplicationListener<AbstractAuthenticationFailureEvent> {

    @Override
    public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
        Authentication authentication = event.getAuthentication();
        AuthenticationException exception = event.getException();
        log.info("用户:{} 登录异常:{}", authentication.getPrincipal(), exception.getLocalizedMessage());
    }
}
