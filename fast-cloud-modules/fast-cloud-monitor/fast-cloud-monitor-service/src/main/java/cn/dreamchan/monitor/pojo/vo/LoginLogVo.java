package cn.dreamchan.monitor.pojo.vo;


import java.time.LocalDateTime;


import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;


/**
 * 系统访问记录
 *
 * @author DreamChan
 */
@Data
public class LoginLogVo {

	// 访问ID
	private Long loginLogId;

	//用户Id
	private Long userId;

	// 用户账号
	@Excel(name = "用户账号", width = 20)
	private String userName;

	// 登录状态（0成功 1失败）
	private String status;

	// 登录IP地址
	@Excel(name = "登录IP地址", width = 20)
	private String ipaddr;

	// 登录时间
	@Excel(name = "登录时间", width = 20)
	private LocalDateTime loginTime;

	// 浏览器类型
	@Excel(name = "浏览器类型", width = 20)
	private String browserName;

	// 操作系统
	@Excel(name = "操作系统", width = 20)
	private String osName;

	// 记录信息
	@Excel(name = "记录信息", width = 20)
	private String message;

}