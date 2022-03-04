package org.lyf.utillity.basic;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * 通用的字符串工具类
 *
 * @author L.Y.F
 */
public class StrUtil {

    // 默认分隔符
    private static final String DEFAULT_SEPERATOR = "_";

    /**
     * 生成UUID
     *
     * @return
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成简单的UUID（去掉横岗并且转小写）
     *
     * @return 随机UUID
     */
    public static String generateSimpleUUID() {
        return generateUUID().replaceAll("-", "").toLowerCase();
    }

    /**
     * 生成指定长度的随机密钥
     *
     * @param length
     *
     * @return
     */
    public static String generateRandomSecretKey(int length) {
        int[] array = new int[length];
        char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            while (true) {
                array[i] = (int) (Math.random() * 1000);
                if ((array[i] > 64 && array[i] < 91) || (array[i] > 96 && array[i] < 123) || (array[i] > 47 && array[i] < 58))
                    break;
            }
            chars[i] = (char) array[i];
        }
        return new String(chars);
    }

    /**
     * 截取集合中的指定元素个数，用指定的分隔符生成字符串
     *
     * @param result
     * @param separator
     *
     * @return
     */
    public static String collection2Str(Collection<?> result, String separator) {
        String str = "";
        if (CollectionUtils.isNotEmpty(result)) {
            str = Joiner.on(separator).skipNulls().join(result);
        }
        return str;
    }

    /**
     * 截取集合中的指定元素个数，用指定的分隔符生成字符串
     *
     * @param result
     * @param maxLen = 0 取集合中所有元素组装字符串
     * @param separator
     *
     * @return
     */
    public static String subList2Str(List<?> result, int maxLen, String separator) {
        String str = "";
        if (CollectionUtils.isNotEmpty(result)) {
            int resultSize = result.size();
            if (maxLen >= 1) {
                maxLen = maxLen > resultSize ? resultSize : maxLen;
                result = result.subList(0, maxLen);
            }
            str = Joiner.on(separator).skipNulls().join(result);
        }
        return str;
    }

    /**
     * 将传入的对象数组按照默认分隔符“_”拼接成字符串
     * <pre>
     * 1. 该方法永远不会返回null
     * 2. 如果要自定义，请使用另一个方法 {@link StrUtil#joinSubKeyBySeper(String, Object...)}
     * </pre>
     *
     * @param objects
     *
     * @return
     */
    public static String joinSubKey(Object... objects) {
        return joinSubKeyBySeper(null, objects);
    }

    /**
     * 将传入的对象数组按照指定分隔符拼接成字符串
     * <pre>
     * 1. 该方法永远不会返回null
     * 2. 如果seperator为null，则使用默认分隔符“_”
     * </pre>
     *
     * @param objects
     * @param seperator
     *
     * @return
     */
    public static String joinSubKeyBySeper(String seperator, Object... objects) {
        if (null != objects) {
            return Joiner.on(null == seperator ? DEFAULT_SEPERATOR : seperator).join(ImmutableList.builder().add(objects).build());
        } else {
            return "";
        }
    }

    /**
     * 将字符串按指定的分隔符切割为int[]
     *
     * @param args
     * @param seperator 分隔符
     *
     * @return
     */
    public static int[] string2IntArr(String args, String seperator) {
        int[] resultArr = null;
        if (StringUtils.isBlank(args) || StringUtils.isEmpty(seperator)) {
            return resultArr;
        }
        String[] arg = split(seperator, args);
        if (null == arg) {
            return resultArr;
        }
        resultArr = new int[arg.length];
        for (int i = 0; i < arg.length; i++) {
            resultArr[i] = Integer.parseInt(arg[i]);
        }
        return resultArr;
    }

    /**
     * 将字符串按指定的分隔符切割为List（此方法返回的List保证不为null）
     *
     * @param args
     * @param seperator 分隔符
     *
     * @return
     */
    public static List<String> str2StringList(String args, String seperator) {
        List<String> resultList = Lists.newArrayList();
        if (StringUtils.isBlank(args) || StringUtils.isEmpty(seperator)) {
            return resultList;
        }
        String[] arg = split(seperator, args);
        if (arg == null) {
            return resultList;
        }
        for (String s : arg) {
            resultList.add(s);
        }
        return resultList;
    }

    /**
     * 将字符串按指定的分隔符切割为List（此方法返回的List保证不为null）
     *
     * @param args
     * @param seperator 分隔符
     *
     * @return
     */
    public static List<Integer> str2IntegerList(String args, String seperator) {
        List<Integer> resultList = Lists.newArrayList();
        if (StringUtils.isBlank(args) || StringUtils.isEmpty(seperator)) {
            return resultList;
        }
        String[] arg = split(seperator, args);
        if (arg == null) {
            return resultList;
        }
        for (String s : arg) {
            if (StringUtils.isNotBlank(s)) {
                resultList.add(Integer.parseInt(s));
            }
        }
        return resultList;
    }

    /**
     * 将字符串按指定的分隔符切割为List（此方法返回的List保证不为null）
     *
     * @param args
     * @param seperator 分隔符
     *
     * @return
     */
    public static List<Long> str2LongList(String args, String seperator) {
        List<Long> resultList = Lists.newArrayList();
        if (StringUtils.isBlank(args) || StringUtils.isEmpty(seperator)) {
            return resultList;
        }
        String[] arg = split(seperator, args);
        if (arg == null) {
            return resultList;
        }
        for (String s : arg) {
            if (StringUtils.isNotBlank(s)) {
                resultList.add(Long.parseLong(s));
            }
        }
        return resultList;
    }

    /**
     * 按照指定的分隔符切分字符串,返回字符数组 该方法会自动忽略空项，并且去掉每项的前后空格
     *
     * @param seperator 指定分隔符，如不指定分隔符，则该方法直接返回null
     * @param text
     *
     * @return
     */
    public static String[] split(String seperator, String text) {
        if (StringUtils.isBlank(seperator) || StringUtils.isBlank(text)) {
            return null;
        }
        return Iterables.toArray(Splitter.on(seperator).trimResults().omitEmptyStrings().split(text), String.class);
    }

    /**
     * 判断字符串中是否全是数字：0到9
     *
     * @param str
     *
     * @return
     */
    public static boolean isNumLegal(String str) {
        if (str == null) {
            return false;
        }
        for (int i = 0; i < str.getBytes().length; i++) {
            char ch = str.charAt(i);
            if (ch < '0' || ch > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     * 字符串超长省略号显示
     *
     * @param value：待处理字符串
     * @param limit：最终期望的字符串长度 <pre>
     * 	if (StringUtils.isBlank(value) || limit <= 3) return value
     *  if (value.length() <= limit) return value
     * </pre>
     *
     * @return
     */
    public static String stringExtractEllipsis(String value, int limit) {
        if (StringUtils.isBlank(value) || limit <= 3) {
            return value;
        }
        if (value.length() > limit) {
            return value.substring(0, limit - 3) + "...";
        } else {
            return value;
        }
    }

    /**
     * 创建StringBuilder对象
     *
     * @return StringBuilder对象
     */
    public static StringBuilder builder() {
        return new StringBuilder();
    }

    /**
     * 反转字符串<br>
     * 例如：abcd =》dcba
     *
     * @param str 被反转的字符串
     * @return 反转后的字符串
     */
    public static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * 将已有字符串填充为规定长度，如果已有字符串超过这个长度则返回这个字符串
     *
     * @param str        被填充的字符串
     * @param filledChar 填充的字符
     * @param len        填充长度
     * @param isPre      是否填充在前
     * @return 填充后的字符串
     */
    public static String fillStr(String str, char filledChar, int len, boolean isPre) {
        final int strLen = str.length();
        if (strLen > len) {
            return str;
        }
        String filledStr = repeat(filledChar, len - strLen);
        return isPre ? filledStr.concat(str) : str.concat(filledStr);
    }

    /**
     * 重复某个字符
     *
     * @param c     被重复的字符
     * @param count 重复的数目，如果小于等于0则返回""
     * @return 重复字符字符串
     */
    public static String repeat(char c, int count) {
        if (count <= 0) {
            return "";
        }
        char[] result = new char[count];
        for (int i = 0; i < count; i++) {
            result[i] = c;
        }
        return new String(result);
    }

    /**
     * 检查字符序列是否为null、""或者空白
     * 空白的定义参考 {@link Character#isWhitespace(char)}
     * <pre>
     *      * StringUtils.isBlank(null)      = true
     *      * StringUtils.isBlank("")        = true
     *      * StringUtils.isBlank(" ")       = true
     *      * StringUtils.isBlank("bob")     = false
     *      * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param cs
     * @return
     */
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查字符序列是否不为 isBlank("")
     * <pre>
     *     StringUtils.isNotBlank(null)      = false
     *     StringUtils.isNotBlank("")        = false
     *     StringUtils.isNotBlank(" ")       = false
     *     StringUtils.isNotBlank("bob")     = true
     *     StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param cs
     * @return
     */
    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

    /**
     * 检查目标字符序列是否为null或者""
     * <pre>
     *     StringUtils.isEmpty(null)      = true
     *     StringUtils.isEmpty("")        = true
     *     StringUtils.isEmpty(" ")       = false
     *     StringUtils.isEmpty("bob")     = false
     *     StringUtils.isEmpty("  bob  ") = false
     * </pre>
     *
     * @param cs
     * @return
     */
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * 检查目标序列是否不为isEmpty()
     * <pre>
     *     StringUtils.isNotEmpty(null)      = false
     *     StringUtils.isNotEmpty("")        = false
     *     StringUtils.isNotEmpty(" ")       = true
     *     StringUtils.isNotEmpty("bob")     = true
     *     StringUtils.isNotEmpty("  bob  ") = true
     * </pre>
     *
     * @param cs
     * @return
     */
    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

}
