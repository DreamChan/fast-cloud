package cn.dreamchan.system.pojo.vo;


import java.time.LocalDateTime;

import cn.afterturn.easypoi.excel.annotation.Excel;


import lombok.Data;


/**
 * 字典类型
 *
 * @author DreamChan
 */
@Data
public class DictTypeVo {


	// 字典主键
	private Long dictTypeId;

	// 字典名称
	@Excel(name = "字典名称", width = 20)
	private String dictName;

	// 字典编码
	@Excel(name = "字典编码", width = 20)
	private String dictCode;

	// 状态（0正常 1停用）
	private String status;

	// 备注
	private String remark;

	// 创建人
	private String createBy;

	// 创建时间
	private LocalDateTime createTime;

	// 更新人
	private String updateBy;

	// 更新时间
	private LocalDateTime updateTime;

}