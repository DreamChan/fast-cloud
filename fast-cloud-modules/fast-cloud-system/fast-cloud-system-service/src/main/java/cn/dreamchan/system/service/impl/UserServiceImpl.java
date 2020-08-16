package cn.dreamchan.system.service.impl;

import cn.dreamchan.common.core.exception.CustomException;
import cn.dreamchan.common.core.utils.StringUtils;
import cn.dreamchan.common.datascope.annotation.DataScope;
import cn.dreamchan.common.security.utils.SecurityUtils;
import cn.dreamchan.system.mapper.*;
import cn.dreamchan.system.pojo.dto.UserEditParam;
import cn.dreamchan.system.pojo.entity.*;
import cn.dreamchan.system.pojo.vo.UserVo;
import cn.dreamchan.system.service.UserPostService;
import cn.dreamchan.system.service.UserRoleService;
import cn.dreamchan.system.service.UserService;
import cn.dreamchan.common.core.base.BaseService;

import cn.dreamchan.system.service.mapstruct.UserMapStruct;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息 服务实现类
 *
 * @author DreamChan
 */
@Service
public class UserServiceImpl extends BaseService<UserMapper, UserEntity> implements UserService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserPostService userPostService;
    @Autowired
    private UserMapStruct userMapStruct;

    @DataScope(deptAlias = "d", userAlias = "u")
    @Override
    public IPage<UserVo> getUserList(Page page, QueryWrapper<UserEntity> wrapper){
        return page.setRecords(this.baseMapper.getUserList(page, wrapper));
    }

    @Override
    public UserVo getUserVoByUserName(String userName) {
        return this.baseMapper.getUserVoByUserName(userName);
    }

    @Override
    public Boolean resetUserPwd(String userName, String encryptPassword) {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(encryptPassword);
        return this.lambdaUpdate().eq(UserEntity::getUserName, userName).update(userEntity);
    }

    @Override
    public boolean updateUserProfile(UserEntity user) {
        return this.lambdaUpdate().eq(UserEntity::getUserId, user.getUserId()).update(user);
    }

    @Override
    public String selectUserRoleGroup(String userName) {
        List<RoleEntity> list = roleMapper.selectRolesByUserName(userName);
        StringBuffer idsStr = new StringBuffer();
        for (RoleEntity role : list) {
            idsStr.append(role.getRoleName()).append(",");
        }
        if (StringUtils.isNotBlank(idsStr.toString())) {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    @Override
    public String selectUserPostGroup(String userName) {
        List<PostEntity> list = postMapper.
                selectPostsByUserName(userName);
        StringBuffer idsStr = new StringBuffer();
        for (PostEntity post : list) {
            idsStr.append(post.getPostName()).append(",");
        }
        if (StringUtils.isNotBlank(idsStr.toString())) {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    @Override
    @Transactional
    public Integer insertUser(UserEditParam user) {
        //保存用户
        UserEntity userEntity = userMapStruct.toEntity(user);
        int rows = this.baseMapper.insert(userEntity);
        Long userId = userEntity.getUserId();

        // 新增用户岗位关联
        insertUserPost(userId, user.getPostIds());
        // 新增用户与角色管理a
        insertUserRole(userId, user.getRoleIds());
        return rows;
    }

    @Override
    @Transactional
    public Integer updateUser(UserEditParam user) {
        Long userId = user.getUserId();

        // 删除用户与角色关联
        LambdaQueryWrapper<UserRoleEntity> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(UserRoleEntity::getUserId, userId);
        userRoleService.remove(queryWrapper1);
        // 新增用户与角色管理
        insertUserRole(userId, user.getRoleIds());

        // 删除用户与岗位关联
        LambdaQueryWrapper<UserPostEntity> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(UserPostEntity::getUserId, userId);
        userPostService.remove(queryWrapper2);
        // 新增用户与岗位管理
        insertUserPost(userId, user.getPostIds());

        UserEntity userEntity = userMapStruct.toEntity(user);
        userEntity.setPassword(null);
        return this.baseMapper.updateById(userEntity);
    }

    /**
     * 新增用户角色信息
     */
    public void insertUserRole(Long userId, Long[] roles) {
        if (StringUtils.isNotNull(roles)) {
            // 新增用户与角色管理
            List<UserRoleEntity> list = new ArrayList<>();
            for (Long roleId : roles) {
                UserRoleEntity ur = new UserRoleEntity();
                ur.setUserId(userId);
                ur.setRoleId(roleId);
                list.add(ur);
            }
            if (list.size() > 0) {
                userRoleService.saveBatch(list);
            }
        }
    }

    /**
     * 新增用户岗位信息
     */
    public void insertUserPost(Long userId, Long[] posts) {
        if (StringUtils.isNotNull(posts)) {
            // 新增用户与岗位管理
            List<UserPostEntity> list = new ArrayList<>();
            for (Long postId : posts) {
                UserPostEntity up = new UserPostEntity();
                up.setUserId(userId);
                up.setPostId(postId);
                list.add(up);
            }
            if (list.size() > 0) {
                userPostService.saveBatch(list);
            }
        }
    }

    /**
     * 校验用户是否允许操作
     *
     * @param user 用户信息
     */
    @Override
    public void checkUserAllowed(UserEditParam user) {
        if (StringUtils.isNotNull(user.getUserId()) && SecurityUtils.isAdmin(user.getUserId())) {
            throw new CustomException("不允许操作超级管理员用户");
        }
    }
}


