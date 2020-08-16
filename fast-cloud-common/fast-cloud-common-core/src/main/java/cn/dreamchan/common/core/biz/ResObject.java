package cn.dreamchan.common.core.biz;


import lombok.Data;

import java.util.Calendar;

/**
 * 返回通用Json数据 封装
 *
 * @author DreamChan
 */
@Data
public class ResObject<T> {

    // 自定义状态码
    private Integer code;
    // 成功标志  true 成功  false 失败
    private Boolean isSuccess;
    // 消息提示
    private String msg;
    // 时间戳
    private Long timestamp;
    // 返回数据
    private T data;


    public ResObject(){

    }

    public ResObject(Integer code, Boolean isSuccess, String msg){
        this.code = code;
        this.isSuccess = isSuccess;
        this.msg = msg;
    }

    public long getTimestamp() {
        if (null == timestamp) {
            return Calendar.getInstance().getTimeInMillis();
        }
        return timestamp;
    }


}
