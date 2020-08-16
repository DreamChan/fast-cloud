package cn.dreamchan.common.core.enums;

/**
 * 状态
 *
 * @author DreamChan
 */
public enum StatusEnum {
    ENABLE("0", "正常"),
    DISABLE("1", "停用");

    private final String code;
    private final String info;

    StatusEnum(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
