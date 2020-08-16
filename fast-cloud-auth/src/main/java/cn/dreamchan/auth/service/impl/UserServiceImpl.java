package cn.dreamchan.auth.service.impl;


import cn.dreamchan.auth.mapper.UserMapper;
import cn.dreamchan.auth.pojo.entity.UserEntity;
import cn.dreamchan.auth.pojo.vo.UserVo;
import cn.dreamchan.auth.service.UserService;
import cn.dreamchan.common.core.base.BaseService;
import cn.dreamchan.common.core.constant.Constants;
import cn.dreamchan.common.core.utils.StringUtils;
import cn.dreamchan.common.security.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 用户信息 服务实现类
 *
 * @author DreamChan
 */
@Service
public class UserServiceImpl extends BaseService<UserMapper, UserEntity> implements UserService {

    @Override
    public UserVo getUserVoByUserName(String userName) {
        return this.baseMapper.getUserVoByUserName(userName);
    }

    @Override
    public Set<String> getRolePermission(Long userId) {
        Set<String> roles = new HashSet<>();
        // 管理员拥有所有权限
        if (SecurityUtils.isAdmin(userId)) {
            roles.add(Constants.DEFAULT_ADMIN_ROLE);
        } else {
            List<String> perms = this.baseMapper.getRolePermissionByUserId(userId);
            for (String perm : perms) {
                if (StringUtils.isNotNull(perm)) {
                    roles.addAll(Arrays.asList(perm.trim().split(",")));
                }
            }
        }
        return roles;
    }

    @Override
    public Set<String> getMenuPermission(Long userId) {
        Set<String> menus = new HashSet<>();
        // 管理员拥有所有权限
        if (SecurityUtils.isAdmin(userId)) {
            menus.add("*:*:*");
        } else {
            List<String> perms = this.baseMapper.getMenuPermsByUserId(userId);
            for (String perm : perms) {
                if (StringUtils.isNotBlank(perm)) {
                    menus.addAll(Arrays.asList(perm.trim().split(",")));
                }
            }
        }
        return menus;
    }

}


