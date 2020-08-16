package cn.dreamchan.system.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

import cn.dreamchan.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;


/**
 * 终端配置表
 *
 * @author DreamChan
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("oauth_client_details")
public class OauthClientDetailsEntity implements Serializable {


	// 终端编号
	@TableId(value = "client_id")
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