package cn.dreamchan.system.service;

import cn.dreamchan.system.pojo.dto.UserEditParam;
import cn.dreamchan.system.pojo.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.dreamchan.system.pojo.entity.UserEntity;

/**
 *
 * 用户信息 服务类
 *
 * @author DreamChan
 */
public interface UserService extends IService<UserEntity>{

    IPage<UserVo> getUserList(Page page, QueryWrapper<UserEntity> wrapper);

    UserVo getUserVoByUserName(String userName);

    Boolean resetUserPwd(String userName, String encryptPassword);

    boolean updateUserProfile(UserEntity user);

    Object selectUserRoleGroup(String userName);

    Object selectUserPostGroup(String userName);

    Integer insertUser(UserEditParam user);

    Integer updateUser(UserEditParam user);

    void checkUserAllowed(UserEditParam user);
}

