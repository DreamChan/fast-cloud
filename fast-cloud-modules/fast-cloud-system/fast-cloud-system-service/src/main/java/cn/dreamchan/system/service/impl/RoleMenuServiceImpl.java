package cn.dreamchan.system.service.impl;

import cn.dreamchan.system.mapper.RoleMenuMapper;
import cn.dreamchan.system.pojo.entity.RoleMenuEntity;
import cn.dreamchan.system.service.RoleMenuService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 角色和菜单关联 服务实现类
 *
 * @author DreamChan
 * @since 2020-07-15
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenuEntity> implements RoleMenuService {

    @Override
    public boolean checkMenuExistRole(Long menuId) {
        int count = this.lambdaQuery().eq(RoleMenuEntity::getMenuId, menuId).count();
        return count > 0 ? true : false;
    }
}

