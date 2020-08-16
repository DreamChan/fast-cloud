package cn.dreamchan.common.core.enums;

/**
 * 操作日志类型
 *
 * @author DreamChan
 */
public enum EventLogEnum {
	QUERY(1, "查询"),
	CREATE(2, "创建"),
	UPDATE(3, "修改"),
	DELETE(4, "删除"),
	IMPORT(5, "导入"),
	EXPORT(6, "导出");

	private Integer code;
	private String description;

	EventLogEnum(final Integer code, final String description) {
		this.code = code;
		this.description = description;
	}


	public Integer getCode(){
		return this.code;
	}

	public String getDescription(){
		return this.description;
	}
}
