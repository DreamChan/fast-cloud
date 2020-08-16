package cn.dreamchan.auth.component;

import cn.dreamchan.common.core.constant.CacheConstants;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * 终端详情服务
 * @author DreamChan
 */
@Component
public class CustomJdbcClientDetailsService extends JdbcClientDetailsService {

    public CustomJdbcClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }


    @Override
    @Cacheable(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#clientId", unless = "#result == null")
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        return super.loadClientByClientId(clientId);
    }
}
