package cn.dreamchan.system.pojo.entity;


import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.dreamchan.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;



/**
 * 菜单权限
 *
 * @author DreamChan
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_menu")
public class MenuEntity extends BaseEntity {


	// 菜单ID
	@TableId(value = "menu_id" , type = IdType.AUTO)
	private Long menuId;

	// 菜单名称
	private String menuName;

	// 权限标识
	private String menuPerms;

	// 路由地址
	private String routerPath;

	// 菜单类型（1-目录 2-菜单 3-按钮）
	private Integer menuType;

	// 父菜单ID
	private Long parentId;

	// 组件路径
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

}