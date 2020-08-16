package cn.dreamchan.common.security.component;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源服务器对外直接暴露URL,如果设置contex-path 要特殊处理
 *
 * @author DreamChan
 */
@Configuration
@ConfigurationProperties(prefix = "security.oauth2")
@RefreshScope
public class PermitProps {

	@Getter
	@Setter
	private List<String> ignoreUrls = new ArrayList<>();


}
