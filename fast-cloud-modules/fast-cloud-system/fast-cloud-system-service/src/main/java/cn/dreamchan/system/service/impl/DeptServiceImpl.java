package cn.dreamchan.system.service.impl;

import cn.dreamchan.common.core.constant.Constants;
import cn.dreamchan.common.core.enums.StatusEnum;
import cn.dreamchan.common.core.exception.CustomException;
import cn.dreamchan.common.core.tree.TreeSelect;
import cn.dreamchan.common.core.utils.TreeUtil;
import cn.dreamchan.system.mapper.DeptMapper;
import cn.dreamchan.system.pojo.vo.DeptVo;
import cn.dreamchan.system.service.DeptService;
import cn.dreamchan.common.core.base.BaseService;
import cn.dreamchan.system.pojo.entity.DeptEntity;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门 服务实现类
 *
 * @author DreamChan
 */
@Service
public class DeptServiceImpl extends BaseService<DeptMapper, DeptEntity> implements DeptService {

    /**
     * 根据角色查询所有的部门
     * @param roleId
     * @return
     */
    @Override
    public List<Integer> selectDeptListByRoleId(Long roleId) {
        return this.baseMapper.selectDeptListByRoleId(roleId);
    }

    @Override
    public List<TreeSelect> buildDeptTreeSelect(List<DeptVo> depts) {
        List<DeptVo> deptTrees = TreeUtil.build(depts, Constants.DEPT_ROOT_ID);
        return TreeUtil.buildTreeSelect(deptTrees);
    }

    /**
     * 新增保存部门信息
     *
     * @param deptEntity 部门信息
     * @return 结果
     */
    @Override
    public int insertDept(DeptEntity deptEntity) {
        DeptEntity parentDeptEntity = this.baseMapper.selectById(deptEntity.getParentId());
        // 如果父节点不为正常状态,则不允许新增子节点
        if (parentDeptEntity.getStatus() == StatusEnum.DISABLE.getCode()) {
            throw new CustomException("部门停用，不允许新增");
        }
        deptEntity.setParentIds(parentDeptEntity.getParentIds() + "," + deptEntity.getParentId());
        return this.baseMapper.insert(deptEntity);
    }
}

