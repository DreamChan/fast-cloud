package cn.dreamchan.common.security.handle;

import cn.dreamchan.common.core.biz.R;
import cn.dreamchan.common.core.biz.ResCodeEnum;
import cn.dreamchan.common.core.utils.JsonUtil;
import cn.dreamchan.common.core.utils.ServletUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权失败
 * @author DreamChan
 */
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        ServletUtils.renderString(response,
                HttpStatus.FORBIDDEN.value(),
                JsonUtil.toJson(R.failure(ResCodeEnum.FORBIDDEN, "授权失败，没有权限访问相关资源")));

    }

}
