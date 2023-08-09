package org.sljl.utillity.exception.platform;

import com.google.common.base.Strings;

/**
 * 请求过期废弃接口异常对象
 *
 * @author L.Y.F
 * @create 2020-12-16 17:20
 */
public class PlatformDeprecatedInterfaceException extends PlatformRuntimeException {

    // 业务码
    private static final int bizCode = -3;
    // 业务码信息
    private static String bizMessage = "请求的接口已过期弃用，建议升级对应的client版本";

    /**
     * 接口过期弃用错误异常对象
     *
     * @see PlatformRuntimeException#PlatformRuntimeException()
     */
    public PlatformDeprecatedInterfaceException() {
        this(String.format("(%d)%s", bizCode, Strings.nullToEmpty(bizMessage)));
    }

    /**
     * 接口过期弃用错误异常对象（可自定义修改错误文案，但是错误码不可修改）
     *
     * @see PlatformRuntimeException#PlatformRuntimeException(String)
     * @param message
     */
    public PlatformDeprecatedInterfaceException(String message) {
        super(String.format("(%d)%s", bizCode, Strings.nullToEmpty(message)));
        bizMessage = message;
    }

    /**
     * 接口过期弃用错错误异常对象（可携带异常堆栈）
     *
     * @see PlatformRuntimeException#PlatformRuntimeException(Throwable)
     * @param cause
     */
    public PlatformDeprecatedInterfaceException(Throwable cause) {
        super(String.format("(%d)%s", bizCode, Strings.nullToEmpty(bizMessage)), cause);
    }

    /**
     * 接口过期弃用错误异常对象（可自定义修改错误文案，但是错误码不可修改；可携带异常堆栈）
     *
     * @see PlatformRuntimeException#PlatformRuntimeException(String, Throwable)
     * @param message
     * @param cause
     */
    public PlatformDeprecatedInterfaceException(String message, Throwable cause) {
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
