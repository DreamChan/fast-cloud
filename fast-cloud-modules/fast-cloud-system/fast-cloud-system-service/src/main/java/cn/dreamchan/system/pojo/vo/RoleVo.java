package cn.dreamchan.system.pojo.vo;


import java.time.LocalDateTime;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;


import lombok.Data;


/**
 * 角色信息
 *
 * @author DreamChan
 */
@Data
public class RoleVo {


	// 角色ID
	private Long roleId;

	// 角色名称
	@Excel(name = "角色名称", width = 20)
	private String roleName;

	// 角色权限字符串
	@Excel(name = "角色权限字符串", width = 20)
	private String roleKey;

	// 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
	private String dataScope;

	// 角色状态（0正常 1停用）
	private String status;

	// 备注
	private String remark;

	// 排序
	@Excel(name = "排序", width = 20)
	private Integer orderNum;

	// 创建人
	private String createBy;

	// 创建时间
	private LocalDateTime createTime;

	// 更新人
	private String updateBy;

	// 更新时间
	private LocalDateTime updateTime;

	/** 菜单组 */
	private Long[] menuIds;

	/** 部门组（数据权限） */
	private Long[] deptIds;

}