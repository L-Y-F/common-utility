package org.sljl.utillity.exception.platform;

import com.google.common.base.Strings;

/**
 * 平台各个系统的业务异常使用该异常对象
 * 各个业务模块可自定义自己的业务码和业务提示文案，但是要求bizCode必须大于0，小于零为系统级别错误禁用
 *
 * @author L.Y.F
 * @create 2020-12-16 14:06
 */
public class PlatformBizException extends PlatformRuntimeException {

    // 业务码
    private int bizCode;
    // 业务码信息
    private String bizMessage;

    /**
     * @see PlatformRuntimeException#PlatformRuntimeException()
     * @param bizCode
     * @param bizMessage
     */
    public PlatformBizException(int bizCode, String bizMessage) {
        super(String.format("(%d)%s", bizCode, Strings.nullToEmpty(bizMessage)));
        this.bizCode = bizCode;
        this.bizMessage = bizMessage;
        if (bizCode < 1) {
            throw new PlatformIllegalRequestException();
        }
    }

    /**
     * @see PlatformRuntimeException#PlatformRuntimeException(String, Throwable)
     * @param bizCode
     * @param bizMessage
     * @param cause
     */
    public PlatformBizException(int bizCode, String bizMessage, Throwable cause) {
        super(String.format("(%d)%s", bizCode, Strings.nullToEmpty(bizMessage)), cause);
        this.bizCode = bizCode;
        this.bizMessage = bizMessage;
        if (bizCode < 1) {
            throw new PlatformIllegalRequestException();
        }
    }

    public int getBizCode() {
        return bizCode;
    }

    public String getBizMessage() {
        return bizMessage;
    }

}
