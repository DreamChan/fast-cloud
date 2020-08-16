package cn.dreamchan.oss.controller;

import cn.dreamchan.common.core.biz.R;
import cn.dreamchan.common.core.biz.ResObject;
import cn.dreamchan.common.core.enums.EventLogEnum;
import cn.dreamchan.common.log.annotation.EventLog;
import cn.dreamchan.oss.config.MinioConfigurationProperties;
import cn.dreamchan.oss.exception.MinioException;
import cn.dreamchan.oss.service.MinioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Minio 对象存储
 *
 * @author DreamChan
 */
@Api(tags = "Minio对象存储管理")
@Slf4j
@RestController
@RequestMapping("/minio")
public class MinioController {

    @Autowired
    private MinioService minioService;

    @Autowired
    private MinioConfigurationProperties minioConfig;

    /**
     * 文件上传
     */
    @ApiOperation("文件上传")
    @EventLog(message = "文件上传", businessType = EventLogEnum.CREATE)
    @PostMapping("/upload")
    public ResObject upload(@RequestParam("file") MultipartFile file) throws IOException, MinioException {
        // 文件存储路径  /2020-01-01/43897583617343545473.jpg
        String filename = file.getOriginalFilename();
        int begin = filename.indexOf(".");
        int last = filename.length();
        String ext = filename.substring(begin, last);
        Path path = Paths.get(LocalDate.now().toString(), Instant.now().toEpochMilli() +  ext);

        minioService.upload(path, file.getInputStream(), file.getContentType());

        Map result = new HashMap();
        result.put("fileName", file.getOriginalFilename());
        result.put("filePath",  "/" + minioConfig.getBucket()+ "/" + path);
        return R.success(result);
    }

    @ApiOperation("获取文件")
    @GetMapping("/{object}")
    public void get(@PathVariable("object") String object, HttpServletResponse response) throws IOException, MinioException {
        InputStream inputStream = minioService.get(Paths.get(object));

        response.addHeader("Content-disposition", "attachment;filename=" + object);
        response.setContentType(URLConnection.guessContentTypeFromName(object));
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }


    @ApiOperation("文件删除")
    @EventLog(message = "文件删除", businessType = EventLogEnum.DELETE)
    @DeleteMapping("/{object}")
    public ResObject remove(@PathVariable("object") String object) throws MinioException {
        Path path = Paths.get(object);
        minioService.remove(path);

        return R.success("删除成功");
    }
}
