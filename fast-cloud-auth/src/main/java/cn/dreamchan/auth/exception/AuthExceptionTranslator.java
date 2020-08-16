package cn.dreamchan.auth.exception;import cn.dreamchan.common.core.biz.R;import cn.dreamchan.common.core.biz.ResCodeEnum;import lombok.extern.slf4j.Slf4j;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.security.authentication.BadCredentialsException;import org.springframework.security.core.userdetails.UsernameNotFoundException;import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;/** * 授权服务器 异常统一处理 * * @author DreamChan */@Slf4jpublic class AuthExceptionTranslator implements WebResponseExceptionTranslator {    @Override    public ResponseEntity translate(Exception e) {        log.error("授权服务器 异常统一处理:异常:{}", e.getMessage(), e);        if (e instanceof InvalidTokenException) {            return new ResponseEntity(R.failure(ResCodeEnum.TOKEN_PAST), HttpStatus.OK);        } else if (e instanceof UsernameNotFoundException) {            return new ResponseEntity(R.failure(ResCodeEnum.LOGIN_NAME), HttpStatus.OK);        } else if (e instanceof InvalidGrantException) {            return new ResponseEntity(R.failure(ResCodeEnum.LOGIN_PASSWORD), HttpStatus.OK);        } else if (e instanceof BadCredentialsException) {            return new ResponseEntity(R.failure(ResCodeEnum.ERROR), HttpStatus.OK);        }        return new ResponseEntity(R.failure(), HttpStatus.OK);    }}