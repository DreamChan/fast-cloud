package cn.dreamchan.oss.factory;

import cn.dreamchan.common.core.biz.ResObject;
import cn.dreamchan.oss.api.RemoteOssService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 服务降级处理
 * @author DreamChan
 */
@Component
public class OssFallbackFactory  implements FallbackFactory<RemoteOssService> {

    @Override
    public RemoteOssService create(Throwable throwable) {

        return new RemoteOssService() {
            @Override
            public ResObject<Map> upload(MultipartFile file) {
                return null;
            }

            @Override
            public void get(String object, HttpServletResponse response) {

            }

            @Override
            public ResObject remove(String object) {
                return null;
            }
        };
    }
}
