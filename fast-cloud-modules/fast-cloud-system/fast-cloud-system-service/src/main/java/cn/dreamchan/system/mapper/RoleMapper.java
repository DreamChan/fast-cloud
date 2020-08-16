package cn.dreamchan.system.mapper;

import cn.dreamchan.system.pojo.entity.RoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


/**
 * 角色信息 Mapper 接口
 *
 * @author DreamChan
 */
public interface RoleMapper extends BaseMapper<RoleEntity> {

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    List<RoleEntity> selectRolePermissionByUserId(Long userId);

    List<RoleEntity> selectRolesByUserName(String userName);

    List<Integer> selectRoleListByUserId(Long userId);
}

