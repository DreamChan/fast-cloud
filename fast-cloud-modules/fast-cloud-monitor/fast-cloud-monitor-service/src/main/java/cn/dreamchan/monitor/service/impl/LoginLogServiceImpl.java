package cn.dreamchan.monitor.service.impl;

import cn.dreamchan.monitor.mapper.LoginLogMapper;
import cn.dreamchan.monitor.service.LoginLogService;
import cn.dreamchan.monitor.pojo.entity.LoginLogEntity;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 系统访问记录 服务实现类
 *
 * @author DreamChan
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLogEntity> implements LoginLogService {

}

