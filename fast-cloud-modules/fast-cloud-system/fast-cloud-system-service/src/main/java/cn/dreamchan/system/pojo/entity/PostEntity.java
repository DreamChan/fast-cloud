package cn.dreamchan.system.pojo.entity;


import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.dreamchan.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;



/**
 * 岗位信息
 *
 * @author DreamChan
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_post")
public class PostEntity extends BaseEntity {


	// 岗位ID
	@TableId(value = "post_id" , type = IdType.AUTO)
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

}