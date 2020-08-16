package cn.dreamchan.monitor.service.mapstruct;

import cn.dreamchan.monitor.pojo.entity.*;
import cn.dreamchan.monitor.pojo.vo.*;
import cn.dreamchan.monitor.pojo.dto.*;
import org.mapstruct.Mapper;
import cn.dreamchan.common.core.base.BaseMapStruct;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface LoginLogMapStruct extends BaseMapStruct<LoginLogVo, LoginLogEntity, LoginLogEditParam, LoginLogPageQueryParam >{

}
