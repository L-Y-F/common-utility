/**
 *
 */
package org.sljl.utillity.utils.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 系统正则工具类
 *
 * @author L.Y.F
 *
 */
public class RegexUtil {

    /** 系统密码规则：匹配6~16位字母、英文字符、英文字母、数字 */
    public static final String PASSWD_REGEX = "[\\w!@\\\\:;\"?'\\[#\\]|.$\\{%\\},^/&*()_+=~`]{6,16}";
    /** 验证邮箱规则 */
    public static final String EMAIL_REGEX = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    /** 4~16个中英文字符限制 */
    public static final String NICKNAME_REGEX = "[a-z|A-Z|\u4e00-\u9fa5]{2,16}";
    /** 中文汉字 */
    public static final String CHINESE_REGEX = "[\\u4e00-\\u9fa5]";
    /** 英文字母 */
    public static final String ENGLISH_REGEX = "[a-z|A-Z]";
    /** Emoji标签 */
    public static final String EMOJI_REGEX = "[\ud83c\udc00-\ud83c\udfff|\ud83d\udc00-\ud83d\udfff|\u2600-\u27ff]";
    /** 正整数正则（不包含0） */
    public static final String NUMERIC_POSITIVE_REGEX = "^[1-9]\\d*$";
    /** 匹配八位生日区间范围1900 ~ 2099年之间 */
    public static final String BIRTHDAY8_REGEX = "(19|20)\\d{2}(0\\d|1[12])([012]\\d|3[01])";
    /** 匹配六位生日 */
    public static final String BIRTHDAY6_REGEX = "\\d{2}(0\\d|1[12])([012]\\d|3[01])";
    /** 匹配4位及以上的豹子号 */
    public static final String LEOPARD_NUMBER_REGEX = "(\\d)\\1{3,}";
    /** 匹配6位顺子,包括递增顺子和递减顺子 */
    public static final String STRAIGHT_NUMBER_REGEX = "(0(?=1)|1(?=2)|2(?=3)|3(?=4)|4(?=5)|5(?=6)|6(?=7)|7(?=8)|8(?=9)){5}|(9(?=8)|8(?=7)|7(?=6)|6(?=5)|5(?=4)|4(?=3)|3(?=2)|2(?=1)|1(?=0)){5}";
    /** 匹配中文、英文、数字、常规特殊字符的正则表达式 */
    private static final String ANY_ROUTINE_REGEX = "^[\\u4e00-\\u9fa5a-zA-Z0-9!@#$%^&*()；“”'，。？《》｛｝【】`·！￥%……&*（）——=、|_+{}\\[\\]:;<>,.?~\\\\/-]+$";

    /**
     * 将source字符串中符合pattern正则规则的字符串全部替换成replace
     *
     * @param source ： 源字符串
     * @param pattern ： 正则表达式
     * @param replace ： 替换字符串
     * @return 返回替换后的source
     */
    public static String replaceAllString(String source, String pattern, String replace) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source);
        return m.replaceAll(replace);
    }

    /**
     * 从source字符串中提取符合pattern正则规则的内容
     *
     * @param source ： 源字符串
     * @param pattern ： 正则表达式
     * @return 返回所有符合正则的集合
     */
    public static String extractString(String source, String pattern) {
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source);
        while (m.find()) {
            sb.append(m.group());
        }
        return sb.toString();
    }

    /**
     * 检查source字符串中是否包含符合pattern正则规则的内容
     *
     * @param source ： 源字符串
     * @param pattern ： 正则表达式
     * @return 返回所有符合正则的集合
     */
    public static boolean isContain(String source, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source);
        return m.find();
    }

    /**
     * 检查source字符串是否与pattern正则规则匹配
     *
     * @param source ： 源字符串
     * @param pattern ： 正则表达式
     * @return 返回所有符合正则的集合
     */
    public static boolean isMatching(String source, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source);
        return m.matches();
    }

}
