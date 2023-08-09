package org.sljl.utillity.exception.platform;

import com.google.common.base.Strings;

/**
 * 平台系统内部错误（所有Server端非业务逻辑错误的未知错误均使用该异常对象抛出）
 *
 * @author L.Y.F
 * @create 2020-12-16 16:51
 */
public class PlatformServerException extends PlatformRuntimeException {

    // 业务码
    private static final int bizCode = -1;
    // 业务码信息
    private static String bizMessage = "系统内部错误!";

    /**
     * 系统错误异常对象
     * @see PlatformRuntimeException#PlatformRuntimeException()
     */
    public PlatformServerException() {
        this(String.format("(%d)%s", bizCode, Strings.nullToEmpty(bizMessage)));
    }

    /**
     * 系统错误异常对象（可自定义修改错误文案，但是错误码不可修改）
     *
     * @see PlatformRuntimeException#PlatformRuntimeException(String)
     * @param message
     */
    public PlatformServerException(String message) {
        super(String.format("(%d)%s", bizCode, Strings.nullToEmpty(message)));
        bizMessage = message;
    }

    /**
     * 系统错误异常对象（可携带异常堆栈）
     *
     * @see PlatformRuntimeException#PlatformRuntimeException(Throwable)
     * @param cause
     */
    public PlatformServerException(Throwable cause) {
        super(String.format("(%d)%s", bizCode, Strings.nullToEmpty(bizMessage)), cause);
    }

    /**
     * 系统错误异常对象（可自定义修改错误文案，但是错误码不可修改；可携带异常堆栈）
     *
     * @see PlatformRuntimeException#PlatformRuntimeException(String, Throwable)
     * @param message
     * @param cause
     */
    public PlatformServerException(String message, Throwable cause) {
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
