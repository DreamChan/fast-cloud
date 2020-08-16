package cn.dreamchan.system.pojo.dto;


import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;


import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * 菜单权限
 *
 * @author DreamChan
 */
@Data
public class MenuEditParam {


    // 菜单ID
    private Long menuId;

    // 菜单名称
    @NotBlank(message = "菜单名称不能为空")
    private String menuName;

    // 权限标识
    private String menuPerms;

    // 路由地址
    private String routerPath;

    // 菜单类型（1-目录 2-菜单 3-按钮）
    private Integer menuType;

    // 父菜单ID
    private Long parentId;

    // 父ID列表
    private String parentIds;

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

    // 创建人
    private String createBy;

    // 创建时间
    private LocalDateTime createTime;

    // 更新人
    private String updateBy;

    // 更新时间
    private LocalDateTime updateTime;


}