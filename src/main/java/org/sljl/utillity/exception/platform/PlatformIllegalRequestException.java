package org.sljl.utillity.exception.platform;

import com.google.common.base.Strings;

/**
 * 非法请求异常对象
 *
 * @author L.Y.F
 * @create 2020-12-16 17:24
 */
public class PlatformIllegalRequestException extends PlatformRuntimeException {

    // 业务码
    private static final int bizCode = -4;
    // 业务码信息
    private static String bizMessage = "非法请求！";

    /**
     * 非法请求错误异常对象
     *
     * @see PlatformRuntimeException#PlatformRuntimeException()
     */
    public PlatformIllegalRequestException() {
        this(String.format("(%d)%s", bizCode, Strings.nullToEmpty(bizMessage)));
    }

    /**
     * 非法请求错误异常对象（可自定义修改错误文案，但是错误码不可修改）
     *
     * @see PlatformRuntimeException#PlatformRuntimeException(String)
     * @param message
     */
    public PlatformIllegalRequestException(String message) {
        super(String.format("(%d)%s", bizCode, Strings.nullToEmpty(message)));
        bizMessage = message;
    }

    /**
     * 非法请求错错误异常对象（可携带异常堆栈）
     *
     * @see PlatformRuntimeException#PlatformRuntimeException(Throwable)
     * @param cause
     */
    public PlatformIllegalRequestException(Throwable cause) {
        super(String.format("(%d)%s", bizCode, Strings.nullToEmpty(bizMessage)), cause);
    }

    /**
     * 非法请求错误异常对象（可自定义修改错误文案，但是错误码不可修改；可携带异常堆栈）
     *
     * @see PlatformRuntimeException#PlatformRuntimeException(String, Throwable)
     * @param message
     * @param cause
     */
    public PlatformIllegalRequestException(String message, Throwable cause) {
        super(String.format("(%d)%s", bizCode, Strings.nullToEmpty(message)), cause);
        bizMessage = message;
    }

    public int getBizCode() {
        return bizCode;
    }

    public String getBizMessage() {
        return bizMessage;
    }

}
