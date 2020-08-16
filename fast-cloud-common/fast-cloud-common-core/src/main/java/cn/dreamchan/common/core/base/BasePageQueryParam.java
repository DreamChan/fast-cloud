package cn.dreamchan.common.core.base;

import cn.dreamchan.common.core.constant.Constants;
import lombok.Data;

/**
 * PageQueryParam 基类
 *
 * @author DreamChan
 */
@Data
public abstract class BasePageQueryParam {

    // 起始页数
    private Integer pageNum;

    // 每页显示记录数
    private Integer pageSize;

    // 开始时间
    private String beginTime;

    // 结束时间
    private String endTime;

    // 排序字段
    private String sortColumn = Constants.DEFAULT_SORT_COLUMN;

    // 排序方式
    private Boolean isAsc = Constants.DEFAULT_IS_ASC;

}
