package cn.dreamchan.auth.controller;

import cn.dreamchan.common.core.biz.R;
import cn.dreamchan.common.core.biz.ResObject;
import cn.dreamchan.common.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * token 管理
 *
 * @author DreamChan
 */
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private TokenStore tokenStore;

    @DeleteMapping("/logout")
    public ResObject logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
        if (StringUtils.isBlank(authHeader)) {
            return R.success();
        }

        String tokenValue = authHeader.replace(OAuth2AccessToken.BEARER_TYPE, "").trim();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
        if (accessToken == null || StringUtils.isBlank(accessToken.getValue())) {
            return R.success();
        }

        // 清空 access token
        tokenStore.removeAccessToken(accessToken);

        // 清空 refresh token
        OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
        tokenStore.removeRefreshToken(refreshToken);

        return R.success();
    }
}
