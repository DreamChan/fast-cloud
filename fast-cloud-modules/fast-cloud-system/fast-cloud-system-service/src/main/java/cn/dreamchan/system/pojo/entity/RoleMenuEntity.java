package cn.dreamchan.system.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * 角色和菜单关联
 *
 * @author DreamChan
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role_menu")
public class RoleMenuEntity implements Serializable {

	// 角色ID
	private Long roleId;

	// 菜单ID
	private Long menuId;

}