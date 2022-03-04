package org.lyf.utillity.example;

import org.lyf.utillity.basic.MapHelper;

import java.util.Map;

/**
 * @author L.Y.F
 * @date 2022/3/4 5:29 下午
 */
public class MapHelperTest {

    public static void main(String[] args) {
        Map<String, String> testMap = MapHelper.builder("aa", "aa", "bb", "bb").build();
        testMap.forEach((k, v) -> System.out.println("key:value = " + k + ":" + v));
    }

}
