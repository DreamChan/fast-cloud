package cn.dreamchan.monitor.service.impl;

import cn.dreamchan.monitor.mapper.OperLogMapper;
import cn.dreamchan.monitor.service.OperLogService;
import cn.dreamchan.monitor.pojo.entity.OperLogEntity;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 操作日志记录 服务实现类
 *
 * @author DreamChan
 */
@Service
public class OperLogServiceImpl extends ServiceImpl<OperLogMapper, OperLogEntity> implements OperLogService {

}

