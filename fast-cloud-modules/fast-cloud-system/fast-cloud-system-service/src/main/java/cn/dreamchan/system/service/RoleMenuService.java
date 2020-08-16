package cn.dreamchan.system.service;

import cn.dreamchan.system.pojo.entity.RoleMenuEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 * 角色和菜单关联 服务类
 *
 * @author DreamChan
 * @since 2020-07-15
 */
public interface RoleMenuService extends IService<RoleMenuEntity>{
    /**
     * 查询菜单是否存在角色
     *
     * @param menuId 菜单ID
     * @return 结果 true 存在 false 不存在
     */
    boolean checkMenuExistRole(Long menuId);
}

