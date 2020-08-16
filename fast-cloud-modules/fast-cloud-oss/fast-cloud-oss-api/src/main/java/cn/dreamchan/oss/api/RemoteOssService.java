package cn.dreamchan.oss.api;

import cn.dreamchan.common.core.biz.ResObject;
import cn.dreamchan.common.core.constant.ServiceNameConstants;
import cn.dreamchan.oss.factory.OssFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 对象存储
 * @author DreamChan
 */
@FeignClient(contextId = "remoteOssService", value = ServiceNameConstants.OSS_SERVICE, fallbackFactory = OssFallbackFactory.class)
public interface RemoteOssService {


    @PostMapping(value = "/minio/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResObject<Map> upload(@RequestPart("file") MultipartFile file);


    @GetMapping("/minio/{object}")
    void get(@PathVariable("object") String object, HttpServletResponse response);


    @DeleteMapping("/minio/{object}")
    ResObject remove(@PathVariable("object") String object);
}
