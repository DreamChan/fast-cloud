package cn.dreamchan.system.mapper;

import cn.dreamchan.system.pojo.entity.MenuEntity;
import cn.dreamchan.system.pojo.vo.MenuVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 菜单权限 Mapper接口
 *
 * @author DreamChan
 */
public interface MenuMapper extends BaseMapper<MenuEntity> {

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    List<String> selectMenuPermsByUserId(Long userId);


    /**
     * 根据用户ID查询菜单
     *
     * @return 菜单列表
     */
    List<MenuEntity> selectMenuTreeAll();

    /**
     * 根据用户ID查询菜单
     *
     * @return 菜单列表
     */
    List<MenuEntity> selectMenuTreeByUserId(Long userId);

    List<MenuEntity> selectMenuList(MenuVo menu);

    List<MenuEntity> selectMenuListByUserId(MenuVo menu);

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    List<Integer> selectMenuListByRoleId(Long roleId);
}

