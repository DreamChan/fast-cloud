package cn.dreamchan.system.service.impl;

import cn.dreamchan.system.mapper.PostMapper;
import cn.dreamchan.system.service.PostService;
import cn.dreamchan.common.core.base.BaseService;
import cn.dreamchan.system.pojo.entity.PostEntity;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位信息 服务实现类
 *
 * @author DreamChan
 */
@Service
public class PostServiceImpl extends BaseService<PostMapper, PostEntity> implements PostService {

    @Override
    public List<Integer> selectPostListByUserId(Long userId) {
        return this.baseMapper.selectPostListByUserId(userId);
    }
}

