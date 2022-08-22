package cn.virowin.user.platform.server.common.response;

import cn.virowin.user.platform.server.common.constant.EnumReturnCode;
import cn.virowin.user.platform.server.common.constant.StatusCodeConstant;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.HashMap;

/**
 * @author virowin
 * @date 2022/8/21 18:48
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CommonResponse {
    private String message;
    private int status;
    private Object data;
    private int code;

    private CommonResponse() {
        super();
        initSuccessResponse();
    }

    public CommonResponse success() {
        initSuccessResponse();
        return this;
    }

    public void setData(Object obj) { data = obj; }

    public CommonResponse success(String message) {
        initSuccessResponse();
        return this;
    }

    public CommonResponse fail(String msg) {
        status = StatusCodeConstant.FAILED_STATUS;
        code = EnumReturnCode.SERVER_ERROR.getCode();
        message = msg;
        return this;
    }

    public CommonResponse fail(int cod, String msg) {
        status = StatusCodeConstant.FAILED_STATUS;
        code = cod;
        message = msg;
        return this;
    }

    public CommonResponse fail(EnumReturnCode enumReturnCode) {
        status = StatusCodeConstant.FAILED_STATUS;
        code = enumReturnCode.getCode();
        message = enumReturnCode.getMessage();
        return this;
    }

    public static CommonResponse createCommonResponse() {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.success();
        return commonResponse;
    }

    public static CommonResponse createCommonResponse(Object obj) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.success();
        commonResponse.setData(obj);
        return commonResponse;
    }

    public void initSuccessResponse() {
        status =  StatusCodeConstant.SUCCESS_STATUS;
        code = EnumReturnCode.SUCCESS.getCode();
        message = EnumReturnCode.SUCCESS.getMessage();
    }
}