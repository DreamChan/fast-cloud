package cn.dreamchan.system.service.impl;

import cn.dreamchan.system.mapper.DictItemMapper;
import cn.dreamchan.system.service.DictItemService;
import cn.dreamchan.common.core.base.BaseService;
import cn.dreamchan.system.pojo.entity.DictItemEntity;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 字典数据 服务实现类
 *
 * @author DreamChan
 */
@Service
public class DictItemServiceImpl extends BaseService<DictItemMapper, DictItemEntity> implements DictItemService {

    @CacheEvict(value = "redisCache", key = "'dictType:'+ #entity.getDictCode()")
    @Override
    public boolean save(DictItemEntity entity) {
        return super.save(entity);
    }

    @CacheEvict(value = "redisCache", key = "'dictType:'+ #entity.getDictCode()")
    @Override
    public boolean updateById(DictItemEntity entity) {
        return super.updateById(entity);
    }

    @CacheEvict(value = "redisCache", key = "'dictType:'+ #entity.getDictCode()")
    @Override
    public boolean removeByIds(DictItemEntity entity, Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @Cacheable(value = "redisCache", key = "'dictType:'+ #dictCode")
    @Override
    public List<DictItemEntity> getItemByDictCode(String dictCode) {
        return this.lambdaQuery().eq(DictItemEntity::getDictCode, dictCode).list();
    }


}

