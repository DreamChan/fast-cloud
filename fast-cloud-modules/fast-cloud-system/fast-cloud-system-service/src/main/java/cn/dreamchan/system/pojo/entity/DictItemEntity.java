package cn.dreamchan.system.pojo.entity;



import com.baomidou.mybatisplus.annotation.*;
import cn.dreamchan.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 字典数据
 *
 * @author DreamChan
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_dict_item")
public class DictItemEntity extends BaseEntity {

	// 字典编码
	@TableId(value = "dict_item_id" , type = IdType.AUTO)
	private Long dictItemId;

	// 字典类型
	private String dictCode;

	// 字典项名称
	private String itemName;

	// 字典项键值
	private String itemValue;

	// 状态（0正常 1停用）
	private String status;

	// 备注
	private String remark;


}