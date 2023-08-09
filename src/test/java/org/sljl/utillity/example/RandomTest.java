package org.sljl.utillity.example;

import com.google.common.collect.Lists;
import org.sljl.utillity.random.RandomUtil;

import java.util.List;

/**
 * @author L.Y.F
 * @date 2022/3/4 2:38 下午
 */
public class RandomTest {

    public static void main(String[] args) {
        List<String> seedList = Lists.newArrayList("一", "二", "三", "四", "五", "六", "七", "八", "九", "十");

        for (int i=0;i<10;i++) {
            System.out.println("int第"+(i+1)+"次随机结果为" + RandomUtil.getRandRange(2, 5));
            System.out.println("long第"+(i+1)+"次随机结果为" + RandomUtil.getRandRange(2L, 5L));
            System.out.println(RandomUtil.getSeedPoolsRandom(seedList));
        }

    }

}
