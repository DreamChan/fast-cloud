package cn.dreamchan.system.service.impl;

import cn.dreamchan.common.core.constant.Constants;
import cn.dreamchan.common.core.enums.LinkEnum;
import cn.dreamchan.common.core.enums.MenuTypeEnum;
import cn.dreamchan.common.core.tree.TreeSelect;
import cn.dreamchan.common.core.utils.StringUtils;
import cn.dreamchan.common.core.utils.TreeUtil;
import cn.dreamchan.common.security.utils.SecurityUtils;
import cn.dreamchan.system.mapper.MenuMapper;
import cn.dreamchan.system.pojo.entity.MenuEntity;
import cn.dreamchan.system.pojo.vo.MenuVo;
import cn.dreamchan.system.pojo.vo.MetaVo;
import cn.dreamchan.system.pojo.vo.RouterVo;
import cn.dreamchan.system.service.MenuService;
import cn.dreamchan.common.core.base.BaseService;

import cn.dreamchan.system.service.mapstruct.MenuMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 菜单权限 服务实现类
 *
 * @author DreamChan
 */
@Service
public class MenuServiceImpl extends BaseService<MenuMapper, MenuEntity> implements MenuService {

    @Autowired
    private MenuMapStruct menuMapStruct;

    /**
     * 获取菜单集合
     *
     * @param
     * @return 菜单权限信息
     */
    public Set<String> getMenuListByUserId(Long userId) {
        Set<String> menus = new HashSet<>();
        // 管理员拥有所有权限
        if (SecurityUtils.isAdmin(userId)) {
            menus.add("*:*:*");
        } else {
            List<String> perms = this.baseMapper.selectMenuPermsByUserId(userId);
            for (String perm : perms) {
                if (StringUtils.isNotBlank(perm)) {
                    menus.addAll(Arrays.asList(perm.trim().split(",")));
                }
            }
        }
        return menus;
    }

    /**
     * 获取用户的菜单列表
     *
     * @param userId 用户ID
     * @return
     */
    @Override
    public List<MenuVo> selectMenuTreeByUserId(Long userId) {
        List<MenuEntity> menus = null;
        if (SecurityUtils.isAdmin(userId)) {
            menus = this.baseMapper.selectMenuTreeAll();
        } else {
            menus = this.baseMapper.selectMenuTreeByUserId(userId);
        }
        return TreeUtil.build(menuMapStruct.toVoList(menus), Constants.MENU_ROOT_ID);
    }


    /**
     * 根据用户查询系统菜单列表
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<MenuVo> selectMenuList(Long userId) {
        return selectMenuList(new MenuVo(), userId);
    }

    /**
     * 根据用户查询系统菜单列表
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<MenuVo> selectMenuList(MenuVo menu, Long userId) {
        List<MenuEntity> menuList = null;
        // 管理员显示所有菜单信息
        if (SecurityUtils.isAdmin(userId)) {
            menuList = this.baseMapper.selectMenuList(menu);
        } else {
            menuList = this.baseMapper.selectMenuListByUserId(menu);
        }
        return menuMapStruct.toVoList(menuList);
    }

    /**
     * 查询是否存在子菜单
     *
     * @param menuId 菜单ID
     * @return
     */
    @Override
    public boolean hasChildByMenuId(Long menuId) {
        int count = this.lambdaQuery().eq(MenuEntity::getParentId, menuId).count();
        return count > 0 ? true : false;

    }

    /**
     * 生成前端菜单选择树
     * @param menus
     * @return
     */
    @Override
    public List<TreeSelect> buildMenuTreeSelect(List<MenuVo> menus) {
        List<MenuVo> menuTrees = TreeUtil.build(menus, Constants.MENU_ROOT_ID);
        return TreeUtil.buildTreeSelect(menuTrees);
    }

    /**
     * 根据角色获取选中的菜单
     * @param roleId
     * @return
     */
    @Override
    public List<Integer> selectMenuListByRoleId(Long roleId) {
        return this.baseMapper.selectMenuListByRoleId(roleId);
    }


    /**
     * 构建前端路由所需要的路由
     *
     * @param menus 路由列表
     * @return 路由列表
     */
    @Override
    public List<RouterVo> buildRouters(List<MenuVo> menus) {
        List<RouterVo> routers = new LinkedList<>();
        for (MenuVo menu : menus) {
            RouterVo router = new RouterVo();
            router.setHidden("1".equals(menu.getVisible()));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon()));
            List<MenuVo> cMenus = menu.getChildren();
            if (!cMenus.isEmpty() && cMenus.size() > 0 && menu.getMenuType() == MenuTypeEnum.DIRECTORY.getCode()) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildRouters(cMenus));
            } else if (isMeunFrame(menu)) {
                List<RouterVo> childrenList = new ArrayList<>();
                RouterVo children = new RouterVo();
                children.setPath(menu.getRouterPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getRouterPath()));
                children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    public String getRouteName(MenuVo menu) {
        String routerName = StringUtils.capitalize(menu.getRouterPath());
        // 非外链并且是一级目录（类型为目录）
        if (isMeunFrame(menu)) {
            routerName = "";
        }
        return routerName;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(MenuVo menu) {
        String routerPath = menu.getRouterPath();
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getParentId().intValue() && MenuTypeEnum.DIRECTORY.getCode() == menu.getMenuType()
                && LinkEnum.NOT_LINK.getCode() == menu.getIsLink()) {
            routerPath = "/" + menu.getRouterPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMeunFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(MenuVo menu) {
        String component = Constants.LAYOUT;
        if (StringUtils.isNotBlank(menu.getComponent()) && !isMeunFrame(menu)) {
            component = menu.getComponent();
        }
        return component;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMeunFrame(MenuVo menu) {
        return menu.getParentId().intValue() == 0 && MenuTypeEnum.DIRECTORY.getCode() == menu.getMenuType()
                && LinkEnum.NOT_LINK.getCode() == menu.getIsLink();
    }
}

