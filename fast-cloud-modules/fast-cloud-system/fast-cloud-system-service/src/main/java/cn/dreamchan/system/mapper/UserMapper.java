package cn.dreamchan.system.mapper;

import cn.dreamchan.system.pojo.entity.UserEntity;
import cn.dreamchan.system.pojo.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息 Mapper 接口
 *
 * @author DreamChan
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    UserVo getUserVoByUserName(String userName);

    List<UserVo> getUserList(Page page, @Param(Constants.WRAPPER) QueryWrapper<UserEntity> wrapper);
}

