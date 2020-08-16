package cn.dreamchan.system.service;

import cn.dreamchan.common.core.tree.TreeSelect;
import cn.dreamchan.system.pojo.vo.MenuVo;
import cn.dreamchan.system.pojo.vo.RouterVo;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.dreamchan.system.pojo.entity.MenuEntity;

import java.util.List;
import java.util.Set;

/**
 *
 * 菜单权限 服务类
 *
 * @author DreamChan
 */
public interface MenuService extends IService<MenuEntity>{

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> getMenuListByUserId(Long userId);

    /**
     * 根据用户ID查询菜单树信息
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<MenuVo> selectMenuTreeByUserId(Long userId);

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    List<RouterVo> buildRouters(List<MenuVo> menus);

    /**
     * 根据用户查询系统菜单列表
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<MenuVo> selectMenuList(Long userId);

    /**
     * 根据用户查询系统菜单列表
     *
     * @param menu 菜单信息
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<MenuVo> selectMenuList(MenuVo menu, Long userId);

    /**
     * 是否存在菜单子节点
     *
     * @param menuId 菜单ID
     * @return 结果 true 存在 false 不存在
     */
    boolean hasChildByMenuId(Long menuId);

    /**
     * 生成前端菜单选择树
     * @param menus
     * @return
     */
    List<TreeSelect> buildMenuTreeSelect(List<MenuVo> menus);

    /**
     * 根据角色获取选中的菜单
     * @param roleId
     * @return
     */
    List<Integer> selectMenuListByRoleId(Long roleId);
}

