package cn.dreamchan.common.core.constant;

/**
 * 系统常量
 *
 * @author DreamChan
 */
public interface Constants {

    /**
     * admin 用户id
     */
    Long ADMIN_USER_ID = 1L;

    /**
     * admin 角色id
     */
    Long ADMIN_ROLE_ID = 1L;

    /**
     * 部门 root节点id
     */
    String DEPT_ROOT_ID = "0";

    /**
     * 菜单 root节点id
     */
    String MENU_ROOT_ID = "0";

    /**
     * 角色名称前缀
     */
    String DEFAULT_ROLE_PREFIX = "ROLE_";

    /**
     * admin 角色名称
     */
    String DEFAULT_ADMIN_ROLE = "admin";

    /**
     * 验证码 redis key
     */
    String CAPTCHA_CODE_KEY = "captcha:code:";

    /**
     * 登录用户 redis key
     */
    String LOGIN_TOKEN_KEY = "login:tokens:";

    /**
     * 验证码有效期 单位:秒
     */
    Integer CAPTCHA_EXPIRATION = 120;

    /**
     * 令牌
     */
    String TOKEN = "token";

    /**
     * 令牌前缀
     */
    String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌前缀
     */
    String LOGIN_USER_KEY = "login_user_key:";

    /**
     * Layout组件标识
     */
    String LAYOUT = "Layout";

    /**
     * 默认排序字段
     */
    String DEFAULT_SORT_COLUMN = "createTime";

    /**
     * 默认排序方式
     */
    boolean DEFAULT_IS_ASC = false;

}
