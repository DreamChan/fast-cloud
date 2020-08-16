package cn.dreamchan.system.pojo.vo;

import cn.dreamchan.common.core.tree.TreeVoFeature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * 部门
 *
 * @author DreamChan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptVo implements TreeVoFeature<DeptVo> {


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


	/** 子部门 */
	private List<DeptVo> children = new ArrayList<>();

	public DeptVo(DeptVo deptVo) {

	}

	@Override
	public void putChildrenList(List<DeptVo> children) {
		this.children = children;
	}

	@Override
	public List<DeptVo> getChildrenList() {
		return this.children;
	}

	@Override
	public String findNodeId() {
		return String.valueOf(this.deptId);
	}

	@Override
	public String findParentNodeId() {
		return String.valueOf(this.parentId);
	}

	@Override
	public String getTreeLabel() {
		return this.getDeptName();
	}
}