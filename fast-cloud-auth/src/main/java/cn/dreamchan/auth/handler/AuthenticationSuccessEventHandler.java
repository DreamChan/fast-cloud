package cn.dreamchan.auth.handler;

import cn.dreamchan.common.core.utils.IpUtils;
import cn.dreamchan.common.core.utils.ServletUtils;
import cn.dreamchan.common.core.utils.StringUtils;
import cn.dreamchan.common.security.domain.LoginUser;
import cn.dreamchan.monitor.api.RemoteLogService;
import cn.dreamchan.monitor.pojo.LoginLogEditParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 认证成功后的处理
 *
 * @author DreamChan
 */
@Slf4j
@Component
public class AuthenticationSuccessEventHandler implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private RemoteLogService logService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {

        Authentication authentication = event.getAuthentication();

        log.info("登录成功! 用户：{} ", authentication.getPrincipal());

        if (StringUtils.isNotNull(authentication.getAuthorities())
                && authentication.getPrincipal() instanceof LoginUser) {

            LoginUser user = (LoginUser) authentication.getPrincipal();
            String username = user.getUsername();

            LoginLogEditParam loginLog = new LoginLogEditParam();
            loginLog.setUserName(username);
            loginLog.setUserId(user.getUserId());
            loginLog.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
            loginLog.setLoginTime(LocalDateTime.now());
            loginLog.setStatus("0");
            loginLog.setMessage("登录成功");

            // 记录用户登录日志
            logService.saveLoginLog(loginLog);
        }
    }
}
