package cn.dreamchan.system.service.impl;

import cn.dreamchan.system.mapper.RoleDeptMapper;
import cn.dreamchan.system.pojo.entity.RoleDeptEntity;
import cn.dreamchan.system.service.RoleDeptService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 角色和部门关联 服务实现类
 *
 * @author DreamChan
 * @since 2020-07-15
 */
@Service
public class RoleDeptServiceImpl extends ServiceImpl<RoleDeptMapper, RoleDeptEntity> implements RoleDeptService {

}

