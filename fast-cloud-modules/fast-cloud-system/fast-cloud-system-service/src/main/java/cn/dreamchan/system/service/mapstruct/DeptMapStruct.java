package cn.dreamchan.system.service.mapstruct;

import cn.dreamchan.system.pojo.entity.*;
import cn.dreamchan.system.pojo.vo.*;
import cn.dreamchan.system.pojo.dto.*;
import org.mapstruct.Mapper;
import cn.dreamchan.common.core.base.BaseMapStruct;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface DeptMapStruct extends BaseMapStruct<DeptVo, DeptEntity, DeptEditParam, DeptPageQueryParam >{

}
