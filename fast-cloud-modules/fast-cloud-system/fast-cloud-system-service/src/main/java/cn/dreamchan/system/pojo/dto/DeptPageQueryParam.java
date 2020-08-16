package cn.dreamchan.system.pojo.dto;


import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;


import cn.dreamchan.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门
 *
 * @author DreamChan
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class DeptPageQueryParam extends BasePageQueryParam {

	// 部门id
	private Long deptId;

	// 部门名称
	private String deptName;

	// 父部门id
	private Long parentId;

	// 父部门ids
	private String parentIds;

	// 负责人
	private String leader;

	// 联系电话
	private String phone;

	// 邮箱
	private String email;

	// 描述
	private String description;

	// 备注
	private String remark;

	// 排序
	private Integer orderNum;

	// 状态（0正常 1停用）
	private String status;

	// 创建人
	private String createBy;

	// 创建时间
	private LocalDateTime createTime;

	// 更新人
	private String updateBy;

	// 更新时间
	private LocalDateTime updateTime;

}