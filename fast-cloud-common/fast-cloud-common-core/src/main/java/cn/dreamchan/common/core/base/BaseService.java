package cn.dreamchan.common.core.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * Service 基类
 *
 * @author DreamChan
 */
public class BaseService<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> {

}