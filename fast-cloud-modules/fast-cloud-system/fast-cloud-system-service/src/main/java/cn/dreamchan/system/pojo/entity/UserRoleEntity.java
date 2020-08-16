package cn.dreamchan.system.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * 用户和角色关联
 *
 * @author DreamChan
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user_role")
public class UserRoleEntity implements Serializable {


	// 用户ID
	private Long userId;

	// 角色ID
	private Long roleId;

}