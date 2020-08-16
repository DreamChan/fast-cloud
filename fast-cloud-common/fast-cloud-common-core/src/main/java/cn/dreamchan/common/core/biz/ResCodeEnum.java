package cn.dreamchan.common.core.biz;


/**
 * 自定义错误类型
 *
 * @author DreamChan
 */
public enum ResCodeEnum {

    SUCCESS(200, "成功"),
    FAILURE(400, "失败"),
    UNAUTHORIZED(401, "未认证"),
    FORBIDDEN(403, "未授权"),
    NOT_FOUND(404, "不存在"),
    INTERNAL_SERVER_ERROR(500, "内部错误"),

    // token异常  3开头
    TOKEN_PAST(301, "token过期"),
    TOKEN_ERROR(302, "token异常"),
    // 登录异常
    LOGIN_ERROR(303, "登录异常"),
    LOGIN_LOCK(304, "用户被禁用"),

    LOGIN_NAME(305, "用户名错误"),
    LOGIN_NAME_NULL(306, "用户名为空"),
    LOGIN_PASSWORD(307, "密码错误"),
    LOGIN_CODE(308, "验证码错误"),
    LOGOUT_CODE(309, "退出失败，token 为空"),
    LOGIN_TENTANT(400, "租户不存在或者租户停用中"),


    // crud异常，4开头
    CRUD_SAVE_FAIL(403, "添加失败"),
    CRUD_UPDATE_FAIL(404, "更新失败"),
    CRUD_DELETE_FAIL(405, "删除失败"),
    CRUD_DELETE_NOT(406, "不允许删除"),
    CRUD_VALID_NOT(407, "字段校验异常"),
    CRUD_NOT_OPERATE(408, "无操作权限"),
    CRUD_LOCK_OPERATE(409, "没有获取到锁"),

    // 默认错误
    ERROR(9999, "错误");

    private final int code;

    private final String msg;

    ResCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
