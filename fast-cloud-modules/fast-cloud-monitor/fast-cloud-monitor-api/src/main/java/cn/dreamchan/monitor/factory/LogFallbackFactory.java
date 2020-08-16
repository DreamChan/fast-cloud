package cn.dreamchan.monitor.factory;

import cn.dreamchan.common.core.biz.ResObject;
import cn.dreamchan.monitor.api.RemoteLogService;
import cn.dreamchan.monitor.pojo.LoginLogEditParam;
import cn.dreamchan.monitor.pojo.OperLogEditParam;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 服务降级处理
 * @author DreamChan
 */
@Component
public class LogFallbackFactory implements FallbackFactory<RemoteLogService> {


    @Override
    public RemoteLogService create(Throwable throwable) {
        return new RemoteLogService() {

            @Override
            public ResObject saveOperLog(OperLogEditParam operLogEditParam) {
                return null;
            }

            @Override
            public ResObject saveLoginLog(LoginLogEditParam loginLogEditParam) {
                return null;
            }
        };
    }
}
