package cn.dreamchan.common.core.exception;

/**
 * 自定义异常
 *
 * @author DreamChan
 */
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String code;

    private String message;

    public CustomException(String message) {
        this.message = message;
    }

    public CustomException(String code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return this.code;
    }
}
