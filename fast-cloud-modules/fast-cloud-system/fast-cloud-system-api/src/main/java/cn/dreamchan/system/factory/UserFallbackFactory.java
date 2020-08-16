package cn.dreamchan.system.factory;

import cn.dreamchan.common.core.biz.ResObject;
import cn.dreamchan.system.api.RemoteUserService;
import cn.dreamchan.system.pojo.UserInfo;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 服务降级处理
 *
 * @author DreamChan
 */
@Component
public class UserFallbackFactory implements FallbackFactory<RemoteUserService> {


    @Override
    public RemoteUserService create(Throwable throwable) {

        return new RemoteUserService() {
            @Override
            public ResObject<UserInfo> getUserInfo(String username) {
                return null;
            }
        };
    }
}
