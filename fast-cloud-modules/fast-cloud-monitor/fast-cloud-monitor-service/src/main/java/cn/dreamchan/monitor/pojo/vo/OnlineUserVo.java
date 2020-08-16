package cn.dreamchan.monitor.pojo.vo;

import lombok.Data;

/**
 * 在线用户
 *
 * @author DreamChan
 */
@Data
public class OnlineUserVo {
    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 用户名称
     * */
    private String userName;

    /**
     * 登陆时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

}
