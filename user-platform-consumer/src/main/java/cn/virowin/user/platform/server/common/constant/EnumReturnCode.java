package cn.virowin.user.platform.server.common.constant;

/**
 * @author virowin
 * @date 2022/8/21 19:54
 */
public enum EnumReturnCode {
    SUCCESS(200, "success")
    ,SERVER_ERROR(500, "service error")
    ,VALIDATION_ERROR(400, "validation failed")
    ;

    private String message;
    private int code;

    private EnumReturnCode(int num, String msg) {
        code = num;
        message = msg;
    }

    public String getMessage() { return message; }
    public int getCode() { return code; }
}
