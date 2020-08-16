package cn.dreamchan.auth.service;


import cn.dreamchan.auth.pojo.entity.UserEntity;
import cn.dreamchan.auth.pojo.vo.UserVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 *
 * 用户信息 服务类
 *
 * @author DreamChan
 */
public interface UserService extends IService<UserEntity> {

    UserVo getUserVoByUserName(String userName);


    Set<String> getRolePermission(Long userId);


    Set<String> getMenuPermission(Long userId);

}

