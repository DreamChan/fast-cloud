package cn.dreamchan.system.pojo.vo;

import java.time.LocalDateTime;
import cn.afterturn.easypoi.excel.annotation.Excel;

import lombok.Data;


/**
 * 终端配置表
 *
 * @author DreamChan
 */
@Data
public class OauthClientDetailsVo {


	// 终端编号
	@Excel(name = "终端编号", width = 20)
	private String clientId;

	// 资源ID标识
	@Excel(name = "资源ID标识", width = 20)
	private String resourceIds;

	// 终端安全码
	@Excel(name = "终端安全码", width = 20)
	private String clientSecret;

	// 终端授权范围
	@Excel(name = "终端授权范围", width = 20)
	private String scope;

	// 终端授权类型
	@Excel(name = "终端授权类型", width = 20)
	private String authorizedGrantTypes;

	// 服务器回调地址
	@Excel(name = "服务器回调地址", width = 20)
	private String webServerRedirectUri;

	// 访问资源所需权限
	@Excel(name = "访问资源所需权限", width = 20)
	private String authorities;

	// 设定终端的access_token的有效时间值（秒）
	@Excel(name = "设定终端的access_token的有效时间值（秒）", width = 20)
	private Integer accessTokenValidity;

	// 设定终端的refresh_token的有效时间值（秒）
	@Excel(name = "设定终端的refresh_token的有效时间值（秒）", width = 20)
	private Integer refreshTokenValidity;

	// 附加信息
	@Excel(name = "附加信息", width = 20)
	private String additionalInformation;

	// 是否登录时跳过授权
	@Excel(name = "是否登录时跳过授权", width = 20)
	private Integer autoapprove;

}