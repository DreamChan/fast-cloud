package cn.dreamchan.system.pojo.entity;


import com.baomidou.mybatisplus.annotation.*;
import cn.dreamchan.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


/**
 * 角色信息
 *
 * @author DreamChan
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role")
public class RoleEntity extends BaseEntity {

	// 角色ID
	@TableId(value = "role_id" , type = IdType.AUTO)
	private Long roleId;

	// 角色名称
	private String roleName;

	// 角色权限字符串
	private String roleKey;

	// 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
	private String dataScope;

	// 角色状态（0正常 1停用）
	private String status;

	// 备注
	private String remark;

	// 排序
	private Integer orderNum;

}