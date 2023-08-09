package org.sljl.utillity.example;

import org.sljl.utillity.regex.RegexUtil;

/**
 * @author L.Y.F
 * @date 2022/3/4 3:42 下午
 */
public class RegexTest {

    public static void main(String[] args) {
        String text = "afnjdka@163.com";
        String text1 = "afnjdka@163";
        // 测试文本是否符合邮件规则
        System.out.println(RegexUtil.isMatching(text, RegexUtil.EMAIL_REGEX));
        // 测试文本是否符合邮件规则
        System.out.println(RegexUtil.isMatching(text1, RegexUtil.EMAIL_REGEX));
        // 查询指定文本是否包含指定的字符
        System.out.println(RegexUtil.isContain(text1, "[@]"));
    }

}
