package cn.dreamchan.system.service.impl;

import cn.dreamchan.system.mapper.UserPostMapper;
import cn.dreamchan.system.pojo.entity.UserPostEntity;
import cn.dreamchan.system.service.UserPostService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户与岗位关联 服务实现类
 *
 * @author DreamChan
 * @since 2020-07-15
 */
@Service
public class UserPostServiceImpl extends ServiceImpl<UserPostMapper, UserPostEntity> implements UserPostService {

}

