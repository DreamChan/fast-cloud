package cn.dreamchan.system.pojo.dto;

import java.time.LocalDateTime;
import lombok.Data;


/**
 * 终端配置表
 *
 * @author DreamChan
 */
@Data
public class OauthClientDetailsEditParam {


    // 终端编号
    private String clientId;

    // 资源ID标识
    private String resourceIds;

    // 终端安全码
    private String clientSecret;

    // 终端授权范围
    private String scope;

    // 终端授权类型
    private String authorizedGrantTypes;

    // 服务器回调地址
    private String webServerRedirectUri;

    // 访问资源所需权限
    private String authorities;

    // 设定终端的access_token的有效时间值（秒）
    private Integer accessTokenValidity;

    // 设定终端的refresh_token的有效时间值（秒）
    private Integer refreshTokenValidity;

    // 附加信息
    private String additionalInformation;

    // 是否登录时跳过授权
    private Integer autoapprove;


}