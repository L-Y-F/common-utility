package org.sljl.utillity.exception.platform;

import com.google.common.base.Strings;

/**
 * 接口请求参数异常使用该异常对象
 *
 * @author L.Y.F
 * @create 2020-12-11 16:57
 */
public class PlatformIllegalArgumentException extends PlatformRuntimeException {

    // 业务码
    private static final int bizCode = -2;
    // 业务码信息
    private static String bizMessage = "请求参数错误!";

    /**
     * 请求参数错误异常对象
     *
     * @see PlatformRuntimeException#PlatformRuntimeException()
     */
    public PlatformIllegalArgumentException() {
        this(String.format("(%d)%s", bizCode, Strings.nullToEmpty(bizMessage)));
    }

    /**
     * 请求参数错误异常对象（可自定义修改错误文案，但是错误码不可修改）
     *
     * @see PlatformRuntimeException#PlatformRuntimeException(String)
     * @param message
     */
    public PlatformIllegalArgumentException(String message) {
        super(String.format("(%d)%s", bizCode, Strings.nullToEmpty(message)));
        bizMessage = message;
    }

    /**
     * 请求参数错错误异常对象（可携带异常堆栈）
     *
     * @see PlatformRuntimeException#PlatformRuntimeException(Throwable)
     * @param cause
     */
    public PlatformIllegalArgumentException(Throwable cause) {
        super(String.format("(%d)%s", bizCode, Strings.nullToEmpty(bizMessage)), cause);
    }

    /**
     * 请求参数错误异常对象（可自定义修改错误文案，但是错误码不可修改；可携带异常堆栈）
     *
     * @see PlatformRuntimeException#PlatformRuntimeException(String, Throwable)
     * @param message
     * @param cause
     */
    public PlatformIllegalArgumentException(String message, Throwable cause) {
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
