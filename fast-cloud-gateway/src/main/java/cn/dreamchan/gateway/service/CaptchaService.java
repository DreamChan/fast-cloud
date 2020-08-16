package cn.dreamchan.gateway.service;

import cn.dreamchan.common.core.biz.R;
import cn.dreamchan.common.core.biz.ResObject;
import cn.dreamchan.common.core.constant.Constants;
import cn.dreamchan.common.core.exception.CustomException;
import cn.dreamchan.common.core.utils.StringUtils;
import cn.dreamchan.common.redis.service.RedisService;
import com.wf.captcha.SpecCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 验证码处理
 *
 * @author DreamChan
 */
@Service
public class CaptchaService {

    @Autowired
    private RedisService redisService;

    /**
     * 生成验证码
     */
    public ResObject createCaptcha() {
        // 唯一标识
        String uuid = UUID.randomUUID().toString();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        // 设置验证码图片宽、高
        SpecCaptcha specCaptcha = new SpecCaptcha(111, 36, 4);
        specCaptcha.setCharType(SpecCaptcha.TYPE_ONLY_NUMBER);
        String verifyCode = specCaptcha.text().toLowerCase();

        redisService.set(verifyKey, verifyCode, Constants.CAPTCHA_EXPIRATION);

        Map<String, Object> result = new HashMap<>();
        result.put("uuid", uuid);
        result.put("captchaImg", specCaptcha.toBase64());
        return R.success(result);
    }


    /**
     * 校验验证码
     */
    public void checkCaptcha(String code, String uuid) {
        if (StringUtils.isBlank(code)) {
            throw new CustomException("验证码不能为空");
        }
        if (StringUtils.isBlank(uuid)) {
            throw new CustomException("验证码已失效");
        }

        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisService.getStr(verifyKey);
        redisService.del(verifyKey);

        if (!code.equalsIgnoreCase(captcha)) {
            throw new CustomException("验证码错误");
        }
    }


}
