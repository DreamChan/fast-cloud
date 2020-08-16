package cn.dreamchan.common.core.base;



import cn.dreamchan.common.core.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.io.Serializable;

/**
 * Controller 基类
 *
 * @author DreamChan
 */
public class BaseController<Q extends BasePageQueryParam, E extends Serializable> {

    /**
     * 封装查询参数
     */
    public QueryWrapper getQueryWrapper(BaseMapStruct baseMapStruct, Q param) {
        QueryWrapper<E> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity((E) baseMapStruct.pageQueryParamToEntity(param));

        String sortColumn = param.getSortColumn();
        String underSortColumn = StringUtils.lowerCamelToLowerUnderscore(sortColumn);
        if (param.getIsAsc()) {
            queryWrapper.orderByAsc(underSortColumn);
        } else {
            queryWrapper.orderByDesc(underSortColumn);
        }
        return queryWrapper;
    }


}
