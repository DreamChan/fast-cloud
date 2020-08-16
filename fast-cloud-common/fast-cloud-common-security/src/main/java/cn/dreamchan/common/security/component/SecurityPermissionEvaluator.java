package cn.dreamchan.common.security.component;

import cn.dreamchan.common.core.utils.StringUtils;
import cn.dreamchan.common.security.domain.LoginUser;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Set;

/**
 * 自定义的权限处理模块
 *
 * @author DreamChan
 */
@Component
public class SecurityPermissionEvaluator implements PermissionEvaluator {

    /**
     * 所有权限标识
     */
    private static final String ALL_PERMISSION = "*:*:*";


    /**
     * 判断 preAuthorize 注解中的权限表达式
     * 实际中可以根据业务需求设计数据库通过targetUrl和permission做更复杂鉴权
     * 当然targetUrl不一定是URL可以是数据Id还可以是管理员标识等,这里根据需求自行设计
     * @Param  authentication  用户身份(在使用hasPermission表达式时Authentication参数默认会自动带上)
     * @Param  targetUrl  请求路径
     * @Param  permission 请求路径权限
     * @Return boolean 是否通过
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission) {
        if ((authentication == null) || (targetUrl == null) || !(permission instanceof String)){
            return false;
        }

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        Set<String> authorities = AuthorityUtils.authorityListToSet(loginUser.getAuthorities());

        if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getAuthorities()) || StringUtils.isNull(permission)) {
            return false;
        }
        return authorities.contains(ALL_PERMISSION) || authorities.contains(StringUtils.trim(permission.toString()));
    }


    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }

}
