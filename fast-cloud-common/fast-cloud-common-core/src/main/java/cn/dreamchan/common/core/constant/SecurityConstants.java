package cn.dreamchan.common.core.constant;

/**
 * 权限相关通用常量
 *
 * @author DreamChan
 */
public interface SecurityConstants {
    /**
     * 令牌类型
     */
    String BEARER_TOKEN_TYPE = "Bearer";

    /**
     * 授权token url
     */
    String AUTH_TOKEN = "/oauth/token";

    /**
     * 注销token url
     */
    String TOKEN_LOGOUT = "/token/logout";

    /**
     * 用户ID字段
     */
    String DETAILS_USER_ID = "user_id";

    /**
     * 用户名字段
     */
    String DETAILS_USERNAME = "username";
}
