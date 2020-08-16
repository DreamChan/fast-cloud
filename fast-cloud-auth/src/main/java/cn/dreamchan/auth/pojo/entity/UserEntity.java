package cn.dreamchan.auth.pojo.entity;


import cn.dreamchan.common.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


/**
 * 用户信息
 *
 * @author DreamChan
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class UserEntity extends BaseEntity {


	// 用户ID
	@TableId(value = "user_id" , type = IdType.AUTO)
	private Long userId;

	// 部门ID
	private Long deptId;

	// 用户账号
	private String userName;

	// 用户昵称
	private String nickName;

	// 用户类型（00系统用户）
	private String userType;

	// 用户邮箱
	private String email;

	// 手机号码
	private String phone;

	// 用户性别（0男 1女 2未知）
	private String sex;

	// 头像地址
	private String avatar;

	// 密码
	private String password;

	// 帐号状态（0正常 1停用）
	private String status;

	// 删除标志（0代表存在 1代表删除）
	private String delStatus;

	// 最后登陆IP
	private String loginIp;

	// 最后登陆时间
	private LocalDateTime loginDate;

	// 备注
	private String remark;

}