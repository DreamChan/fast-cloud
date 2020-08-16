package cn.dreamchan.system.pojo.dto;


import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;


import cn.dreamchan.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 岗位信息
 *
 * @author DreamChan
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PostPageQueryParam extends BasePageQueryParam {


	// 岗位ID
	private Long postId;

	// 岗位编码
	private String postCode;

	// 岗位名称
	private String postName;

	// 状态（0正常 1停用）
	private String status;

	// 备注
	private String remark;

	// 排序
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