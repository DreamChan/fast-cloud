package cn.dreamchan.common.core.enums;

/**
 * 菜单类型
 *
 * @author DreamChan
 */
public enum MenuTypeEnum {
    DIRECTORY(1, "目录"),
    MENU(2, "菜单"),
    BUTTON(3, "按钮");

    private final Integer code;
    private final String info;

    MenuTypeEnum(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    public Integer getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
