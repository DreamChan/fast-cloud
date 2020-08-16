package cn.dreamchan.system.service.impl;

import cn.dreamchan.system.mapper.UserRoleMapper;
import cn.dreamchan.system.pojo.entity.UserRoleEntity;
import cn.dreamchan.system.service.UserRoleService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户和角色关联 服务实现类
 *
 * @author DreamChan
 * @since 2020-07-15
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity> implements UserRoleService {

}

