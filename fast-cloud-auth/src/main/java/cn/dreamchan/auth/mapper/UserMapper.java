package cn.dreamchan.auth.mapper;

import cn.dreamchan.auth.pojo.entity.UserEntity;
import cn.dreamchan.auth.pojo.vo.UserVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 用户信息 Mapper 接口
 *
 * @author DreamChan
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    UserVo getUserVoByUserName(String userName);


    List<String> getMenuPermsByUserId(Long userId);


    List<String> getRolePermissionByUserId(Long userId);
}

