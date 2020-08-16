package cn.dreamchan.common.data.config;

import cn.dreamchan.common.data.converter.CustomLongConverter;
import cn.dreamchan.common.data.jackson.RestJavaTimeModule;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;


/**
 * JacksonConfig
 *
 * @author DreamChan
 */
@Configuration
@AutoConfigureBefore(JacksonAutoConfiguration.class)
public class JacksonConfiguration {

	/** 默认日期时间格式 */
	public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	@Bean
	@ConditionalOnMissingBean
	public Jackson2ObjectMapperBuilderCustomizer customizer() {
		return builder -> {
			builder.serializationInclusion(JsonInclude.Include.NON_NULL);
			builder.indentOutput(true).dateFormat(new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT));
			builder.serializerByType(Long.class, new CustomLongConverter());
			builder.serializerByType(Long.TYPE, new CustomLongConverter());

			builder.modules(new RestJavaTimeModule());
		};
	}

}
