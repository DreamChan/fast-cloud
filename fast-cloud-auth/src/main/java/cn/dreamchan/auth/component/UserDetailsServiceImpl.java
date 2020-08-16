package cn.dreamchan.auth.component;

import cn.dreamchan.auth.pojo.vo.UserVo;
import cn.dreamchan.auth.service.UserService;
import cn.dreamchan.common.core.enums.DelEnum;
import cn.dreamchan.common.core.enums.StatusEnum;
import cn.dreamchan.common.core.exception.CustomException;
import cn.dreamchan.common.security.domain.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * 用户验证处理
 *
 * @author DreamChan
 */
@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, CustomException {

        // 查询登录用户信息
        UserVo user = userService.getUserVoByUserName(username);

        if (user == null) {
            log.info("登录用户：F{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        } else if (DelEnum.DELETED.getCode().equals(user.getDelStatus())) {
            log.info("登录用户：{} 已被删除.", username);
            throw new CustomException("您的账号：" + username + " 已被删除!");
        } else if (StatusEnum.DISABLE.getCode().equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", username);
            throw new CustomException("您的账号：" + username + " 已停用!");
        }

        Set<String> permissions = userService.getMenuPermission(user.getUserId());
        Set<String> roles = userService.getRolePermission(user.getUserId());

        Set<String> dbAuthsSet = new HashSet<>();
        dbAuthsSet.addAll(roles);
        dbAuthsSet.addAll(permissions);

        Collection<? extends GrantedAuthority> authorities = AuthorityUtils
                .createAuthorityList(dbAuthsSet.toArray(new String[0]));
        return new LoginUser(user.getUserId(), user.getUserName(), user.getPassword(), true, true, true, true,
                authorities);
    }

}
