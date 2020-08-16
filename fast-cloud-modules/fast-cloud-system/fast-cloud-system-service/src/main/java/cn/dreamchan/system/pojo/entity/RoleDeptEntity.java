package cn.dreamchan.system.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * 角色和部门关联
 *
 * @author DreamChan
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role_dept")
public class RoleDeptEntity implements Serializable {


	// 角色ID
	private Long roleId;

	// 部门ID
	private Long deptId;

}