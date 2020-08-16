package cn.dreamchan.common.data.handler;

import cn.dreamchan.common.core.biz.R;
import cn.dreamchan.common.core.biz.ResCodeEnum;
import cn.dreamchan.common.core.biz.ResObject;
import cn.dreamchan.common.core.exception.CustomException;
import cn.dreamchan.common.core.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.security.auth.login.AccountExpiredException;
import java.nio.file.AccessDeniedException;

/**
 * 全局异常处理器
 *
 * @author DreamChan
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 业务异常
     */
    @ExceptionHandler(CustomException.class)
    public ResObject businessException(CustomException e) {
        if (StringUtils.isNull(e.getCode())) {
            return R.failure(e.getMessage());
        }
        return R.failure(e.getMessage());
    }

    /**
     * 路径不存在异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResObject handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return R.failure(ResCodeEnum.NOT_FOUND,"路径不存在，请检查路径是否正确");
    }

    /**
     * 权限异常
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResObject handleAuthorizationException(AccessDeniedException e) {
        log.error(e.getMessage());
        return R.failure(ResCodeEnum.FORBIDDEN,"没有相关权限，请联系管理员授权");
    }

    /**
     * 账户超时
     */
    @ExceptionHandler(AccountExpiredException.class)
    public ResObject handleAccountExpiredException(AccountExpiredException e) {
        log.error(e.getMessage(), e);
        return R.failure(e.getMessage());
    }

    /**
     * 用户名不存在
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResObject handleUsernameNotFoundException(UsernameNotFoundException e) {
        log.error(e.getMessage(), e);
        return R.failure(e.getMessage());
    }

    /**
     * 数据绑定异常
     */
    @ExceptionHandler(BindException.class)
    public ResObject validatedBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return R.failure(message);
    }

    /**
     * 数据绑定异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object validExceptionHandler(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return R.failure(message);
    }

    /**
     * 全局异常
     */
    @ExceptionHandler(Exception.class)
    public ResObject handleException(Exception e) {
        log.error(e.getMessage(), e);
        return R.failure(e.getMessage());
    }
}
