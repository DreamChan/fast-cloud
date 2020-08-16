package cn.dreamchan.system.mapper;

import cn.dreamchan.system.pojo.entity.PostEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 岗位信息 Mapper 接口
 *
 * @author DreamChan
 */
public interface PostMapper extends BaseMapper<PostEntity> {

    List<PostEntity> selectPostsByUserName(String userName);

    List<Integer> selectPostListByUserId(Long userId);
}

