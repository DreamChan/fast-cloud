package cn.dreamchan.monitor.pojo.entity;


import java.io.Serializable;
import java.time.LocalDateTime;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 操作日志记录
 *
 * @author DreamChan
 */
@Data
@TableName("mon_oper_log")
public class OperLogEntity implements Serializable {


	// 日志主键
	@TableId(value = "oper_log_id" , type = IdType.AUTO)
	private Long operLogId;

	// 记录信息
	private String message;

	// 业务类型（1-查询 2-新增 3-修改 4-删除 5-导入 6-导出）
	private Integer businessType;

	// 请求方式
	private String requestMethod;

	// 请求参数
	private String requestParam;

	// 访问时间
	private LocalDateTime requestDate;

	// 请求 URL
	private String requestUrl;

	// 执行时间
	private Long executeTime;

	// 用户ID
	private Long userId;

	// 用户账号
	private String userName;

	// 主机地址
	private String operIp;

	// 操作状态（0正常 1异常）
	private Integer status;

}