package cn.dreamchan.system.api;

import cn.dreamchan.common.core.biz.ResObject;
import cn.dreamchan.common.core.constant.ServiceNameConstants;
import cn.dreamchan.system.factory.UserFallbackFactory;
import cn.dreamchan.system.pojo.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用户服务
 * @author DreamChan
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = UserFallbackFactory.class)
public interface RemoteUserService {

    @GetMapping(value = "/user/info/{username}")
    ResObject<UserInfo> getUserInfo(@PathVariable("username") String username);

}
