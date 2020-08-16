package cn.dreamchan.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.dreamchan.system.pojo.entity.DictItemEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


/**
 *
 * 字典数据 服务类
 *
 * @author DreamChan
 */
public interface DictItemService extends IService<DictItemEntity>{

    boolean removeByIds(DictItemEntity entity, Collection<? extends Serializable> idList);

    List<DictItemEntity> getItemByDictCode(String dictCode);
}

