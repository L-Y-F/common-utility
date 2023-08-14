package org.sljl.utillity.utils.email;

import com.sun.mail.util.MailSSLSocketFactory;
import org.sljl.utillity.enums.EmailSmtpEnum;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 邮箱处理工具类
 *
 * @author L.Y.F
 * @date 2023/7/5 21:30
 */
public class EmailUtil {

    /**
     * 邮箱脱敏
     * 根据邮箱名称长度自动计算脱敏字符长度
     * @param str
     * @return
     */
    public static String hideLastChar(String str) {
        String rtnStr = str;
        String regExp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        Pattern p = Pattern.compile(regExp);
        Matcher matcher = p.matcher(rtnStr);
        boolean isMatched = matcher.matches();
        if (isMatched) {
            int s = rtnStr.indexOf("@");
            if (s >= 6) {
                return replace(rtnStr.trim(), s - 3, "****");
            } else if (s >= 3 && s < 6) {
                return replace(rtnStr.trim(), s - 1, "**");
            } else {
                return str;
            }
        } else {
            return rtnStr;
        }
    }

    /**
     * 脱敏字符串替换
     *
     * @param str： str(需脱敏的字符串)
     * @param n：n(脱敏开始位)
     * @param newChar：newChar(脱敏掩码)
     * @return
     */
    private static String replace(String str, int n, String newChar) {
        String s1 = "";
        String s2 = "";
        try {
            s1 = str.substring(0, n - 1);
            s2 = str.substring(n - 1 + newChar.length(), str.length());
            return s1 + newChar + s2;
        } catch (Exception ex) {
            return str;
        }
    }

    /**
     * 创建发送邮件的Session对象
     *
     * @param emailSmtp
     * @param accountName
     * @param accountPwd
     * @return
     * @throws GeneralSecurityException
     */
    private static Session createMailSession(EmailSmtpEnum emailSmtp, String accountName, String accountPwd) throws GeneralSecurityException {
        // SMTP服务器的连接信息
        Properties props = new Properties();
        props.put("mail.smtp.host", emailSmtp.getSmtpHost()); // SMTP主机号
        props.put("mail.smtp.port", emailSmtp.getSmtpPort1()); // 主机端口号
        props.put("mail.smtp.auth", "true"); // 是否需要认证
        props.setProperty("mail.transport.protocol","smtp");
        props.put("mail.smtp.starttls.enable", "true"); // 启用TLS加密
        if (EmailSmtpEnum.SMTP_QQ.equals(emailSmtp)) {
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.ssl.socketFactory", sf);
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        }
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(accountName, accountPwd);
            }
        });
//        // 开启调试模式
//        session.setDebug(true);
        return session;
    }



    public static void main(String[] args) {
        String EMAIL_ACCOUNT = "";
        String EMAIL_PASSWD = "";
        String toEmail = "";
        String title = "测试发送邮件";
        String content = "候选人：张三\n <b>面试时间：</b>2023年7月8日 \n 面试方式：现场面试 \n 面试地点：烈士墓 ";
        try {
            EmailUtil.sendEMail(EmailSmtpEnum.SMTP_QQ, EMAIL_ACCOUNT, EMAIL_PASSWD, toEmail, title, content);
            EmailUtil.sendEMailForHtml(EmailSmtpEnum.SMTP_QQ, EMAIL_ACCOUNT, EMAIL_PASSWD, toEmail, title, content);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    /**
     * 发送普通文本内容的邮件
     *
     * @param emailSmtp
     * @param emailAccount
     * @param emailPwd
     * @param toEmail
     * @param title
     * @param content
     * @throws GeneralSecurityException
     */
    public static void sendEMail(EmailSmtpEnum emailSmtp, String emailAccount, String emailPwd, String toEmail, String title, String content) throws GeneralSecurityException, MessagingException {
        send(emailSmtp, emailAccount, emailPwd, toEmail, title, content, "plain");
    }

    /**
     * 发送HTML格式的内容邮件
     *
     * @param emailSmtp
     * @param emailAccount
     * @param emailPwd
     * @param toEmail
     * @param title
     * @param content
     * @throws GeneralSecurityException
     */
    public static void sendEMailForHtml(EmailSmtpEnum emailSmtp, String emailAccount, String emailPwd, String toEmail, String title, String content) throws GeneralSecurityException, MessagingException {
        send(emailSmtp, emailAccount, emailPwd, toEmail, title, content, "html");
    }

    private static void send(EmailSmtpEnum emailSmtp, String emailAccount, String emailPwd, String toEmail, String title, String content, String subType) throws GeneralSecurityException, MessagingException {
        Session session = EmailUtil.createMailSession(emailSmtp, emailAccount, emailPwd);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(emailAccount));
        msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toEmail));
        msg.setSubject(title,"utf-8");
        msg.setText(content,"utf-8", subType);
        Transport.send(msg);
    }

}
