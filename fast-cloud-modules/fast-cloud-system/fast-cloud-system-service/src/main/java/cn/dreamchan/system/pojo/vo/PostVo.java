package cn.dreamchan.system.pojo.vo;


import java.time.LocalDateTime;

import cn.afterturn.easypoi.excel.annotation.Excel;


import lombok.Data;


/**
 * 岗位信息
 *
 * @author DreamChan
 */
@Data
public class PostVo {


	// 岗位ID
	private Long postId;

	// 岗位编码
	@Excel(name = "岗位编码", width = 20)
	private String postCode;

	// 岗位名称
	@Excel(name = "岗位名称", width = 20)
	private String postName;

	// 状态（0正常 1停用）
	private String status;

	// 备注
	private String remark;

	// 排序
	@Excel(name = "排序", width = 20)
	private Integer orderNum;

	// 创建人
	private String createBy;

	// 创建时间
	private LocalDateTime createTime;

	// 更新人
	private String updateBy;

	// 更新时间
	private LocalDateTime updateTime;

}