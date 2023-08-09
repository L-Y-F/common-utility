package org.sljl.utillity.exception.platform;

/**
 * 平台通用的运行时异常基类
 *
 * @author L.Y.F
 * @create 2020-12-11 16:46
 */
public class PlatformRuntimeException extends RuntimeException {

    /**
     * @see RuntimeException#RuntimeException()
     */
    public PlatformRuntimeException() {
        super();
    }

    /**
     * @see RuntimeException#RuntimeException(String)
     * @param message
     */
    public PlatformRuntimeException(String message) {
        super(message);
    }

    /**
     * @see RuntimeException#RuntimeException(Throwable)
     * @param cause
     */
    public PlatformRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * @see RuntimeException#RuntimeException(String, Throwable)
     * @param message
     * @param cause
     */
    public PlatformRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     *
     * @see RuntimeException#RuntimeException(String, Throwable)
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     * @return
     */
    public PlatformRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
