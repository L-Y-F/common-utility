package org.sljl.utillity.enums;

/**
 * 发送邮件SMTP服务器枚举
 *
 * @author L.Y.F
 * @date 2023/7/9 12:09
 */
public enum EmailSmtpEnum {

    /** QQ邮箱 */
    SMTP_QQ(1, "smtp.qq.com", "465", "587"),
    /** 163邮箱 */
    SMTP_163(2, "smtp.163.com", "465", ""),
    /** 126邮箱 */
    SMTP_126(3, "smtp.126.com", "465", ""),
    /** GMail邮箱 */
    SMTP_GMAIL(4, "smtp.gmail.com", "465", ""),
    /** Outlook邮箱 */
    SMTP_OUTLOOK(5, "smtp-mail.outlook", "465", ""),
    /** Yahoo邮箱 */
    SMTP_YAHOO(6, "smtp.mail.yahoo.com", "465", ""),
    /** Sina邮箱 */
    SMTP_SINA(7, "smtp.sina.com", "465", ""),
    /** 139邮箱 */
    SMTP_139(8, "smtp.139.com", "465", ""),
    /** Foxmail邮箱 */
    SMTP_FOXMAIL(9, "smtp.foxmail.com", "465", ""),
    /** Sohu邮箱 */
    SMTP_SOHU(10, "smtp.sohu.com", "465", ""),

    ;

    private int code;
    private String smtpHost;
    private String smtpPort1;
    private String smtpPort2;

    private EmailSmtpEnum(int code, String smtpHost, String smtpPort1, String smtpPort2) {
        this.code = code;
        this.smtpHost = smtpHost;
        this.smtpPort1 = smtpPort1;
        this.smtpPort2 = smtpPort2;
    }

    public int getCode() {
        return code;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public String getSmtpPort1() {
        return smtpPort1;
    }

    public String getSmtpPort2() {
        return smtpPort2;
    }

    public static EmailSmtpEnum getEnum(Integer code) {
        if (null != code) {
            for (EmailSmtpEnum emailSmtp : EmailSmtpEnum.values()) {
                if (emailSmtp.code == (int)code) {
                    return emailSmtp;
                }
            }
        }
        return null;
    }

}
