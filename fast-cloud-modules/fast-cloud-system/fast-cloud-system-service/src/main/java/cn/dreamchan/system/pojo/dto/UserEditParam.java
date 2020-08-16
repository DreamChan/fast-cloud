package cn.dreamchan.system.pojo.dto;


import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.util.List;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


/**
 * 用户信息
 *
 * @author DreamChan
 */
@Data
public class UserEditParam {


    // 用户ID
    private Long userId;

    // 部门ID
    private Long deptId;

    // 用户账号
    @NotEmpty(message = "账号名称不能为空")
    private String userName;

    // 用户昵称
    private String nickName;

    // 用户类型（00系统用户）
    private String userType;

    // 用户邮箱
    private String email;

    // 手机号码
    @NotEmpty(message = "手机号不能为空")
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

    // 创建人
    private String createBy;

    // 创建时间
    private LocalDateTime createTime;

    // 更新人
    private String updateBy;

    // 更新时间
    private LocalDateTime updateTime;

    /** 角色组 */
    private Long[] roleIds;

    /** 岗位组 */
    private Long[] postIds;


}