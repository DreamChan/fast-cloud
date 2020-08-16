package cn.dreamchan.system.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.dreamchan.common.core.tree.TreeVoFeature;
import cn.dreamchan.system.pojo.entity.MenuEntity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;


/**
 * 菜单权限
 *
 * @author DreamChan
 */
@Data
public class MenuVo implements TreeVoFeature<MenuVo> {


	// 菜单ID
	private Long menuId;

	// 菜单名称
	@Excel(name = "菜单名称", width = 20)
	private String menuName;

	// 权限标识
	@Excel(name = "权限标识", width = 20)
	private String menuPerms;

	// 路由地址
	@Excel(name = "路由地址", width = 20)
	private String routerPath;

	// 菜单类型（1-目录 2-菜单 3-按钮）
	private Integer menuType;

	// 父菜单ID
	private Long parentId;

	// 组件路径
	@Excel(name = "组件路径", width = 20)
	private String component;

	// 是否为外链（0是 1否）
	private Integer isLink;

	// 显示状态（0显示 1隐藏）
	private String visible;

	// 菜单状态（0正常 1停用）
	private String status;

	// 菜单图标
	private String icon;

	// 备注
	private String remark;

	// 排序
	private Integer orderNum;

	// 创建人
	private String createBy;

	// 创建时间
	private LocalDateTime createTime;

	// 更新人
	private String updateBy;

	// 更新时间
	private LocalDateTime updateTime;

	/** 子菜单 */
	private List<MenuVo> children = new ArrayList<>();

	@Override
	public void putChildrenList(List<MenuVo> children) {
		this.children = children;
	}

	@Override
	public List<MenuVo> getChildrenList() {
		return this.children;
	}

	@Override
	public String findNodeId() {
		return String.valueOf(this.menuId);
	}

	@Override
	public String findParentNodeId() {
		return String.valueOf(this.parentId);
	}

	@Override
	public String getTreeLabel() {
		return this.menuName;
	}
}