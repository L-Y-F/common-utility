package org.sljl.utillity.example;

import org.sljl.utillity.basic.StrUtil;

/**
 * @author L.Y.F
 * @date 2022/3/4 5:21 下午
 */
public class StrTest {

    public static void main(String[] args) {
        String str = "我们的大中国";
        char filledChar = '0';
        int len = 10;
        boolean isPre = false;
        System.err.println(StrUtil.fillStr(str, filledChar, len, isPre));
        System.err.println(StrUtil.generateSimpleUUID());
    }

}
