package cn.dreamchan.common.core.enums;

/**
 * 删除状态
 *
 * @author DreamChan
 */
public enum DelEnum {

    NOT_DELETED("0", "未删除"),
    DELETED("1", "已删除");

    private final String code;
    private final String info;

    DelEnum(String code, String info) {
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
