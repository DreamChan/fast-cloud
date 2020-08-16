package cn.dreamchan.system.service;

import cn.dreamchan.system.pojo.dto.RoleEditParam;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.dreamchan.system.pojo.entity.RoleEntity;

import java.util.List;
import java.util.Set;

/**
 *
 * 角色信息 服务类
 *
 * @author DreamChan
 */
public interface RoleService extends IService<RoleEntity>{

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> getRoleListByUserId(Long userId);


    List<Integer> selectRoleListByUserId(Long userId);

    Integer insertRole(RoleEditParam roleEditParam);

    Integer updateRole(RoleEditParam roleEditParam);

    void authDataScope(RoleEditParam roleEditParam);

    void checkRoleAllowed(RoleEditParam roleEditParam);
}

