package org.sljl.utillity.utils.basic;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 车辆相关信息校验工具类
 *
 * @author L.Y.F
 * @create 2020-05-18 17:35
 */
public class VehicleUtil {

    private static Map<Integer, Integer> VIN_MAP_WEIGHTING = Maps.newHashMap();
    private static Map<Character, Integer> VIN_MAP_VALUE = Maps.newHashMap();

    public static void main(String[] args) {
        String vin = "lymtjaa5xea122542";
        System.err.println(VehicleUtil.checkVIN(vin));
        // 领
        //
    }

    /**
     * 校验汽车的17位VIN码是否正确（即车辆识别码）
     *
     * @param vin
     * @return boolean
     */
    public static boolean checkVIN(String vin) {
        boolean reultFlag = false;
        String uppervin = vin.toUpperCase();
        //排除字母O、I
        if (StringUtils.isBlank(vin) || uppervin.indexOf("O") >= 0 || uppervin.indexOf("I") >= 0) {
            reultFlag = false;
        } else {
            if (vin.length() != 17) {
                reultFlag = false;
            } else {
                char[] vinArr = uppervin.toCharArray();
                int amount = 0;
                for (int i = 0; i < vinArr.length; i++) {
                    //VIN码从从第一位开始，码数字的对应值×该位的加权值，计算全部17位的乘积值相加
                    amount += VIN_MAP_VALUE.get(vinArr[i]) * VIN_MAP_WEIGHTING.get(i + 1);
                }
                //乘积值相加除以11、若余数为10，即为字母Ｘ
                if (amount % 11 == 10) {
                    if (vinArr[8] == 'X') {
                        reultFlag = true;
                    } else {
                        reultFlag = false;
                    }
                } else {
                    //VIN码从从第一位开始，码数字的对应值×该位的加权值，
                    //计算全部17位的乘积值相加除以11，所得的余数，即为第九位校验值
                    if (amount % 11 != VIN_MAP_VALUE.get(vinArr[8])) {
                        reultFlag = false;
                    } else {
                        reultFlag = true;
                    }
                }
            }
        }
        return reultFlag;
    }

    static {
        // 初始化相关的值
        VIN_MAP_WEIGHTING.put(1, 8);
        VIN_MAP_WEIGHTING.put(2, 7);
        VIN_MAP_WEIGHTING.put(3, 6);
        VIN_MAP_WEIGHTING.put(4, 5);
        VIN_MAP_WEIGHTING.put(5, 4);
        VIN_MAP_WEIGHTING.put(6, 3);
        VIN_MAP_WEIGHTING.put(7, 2);
        VIN_MAP_WEIGHTING.put(8, 10);
        VIN_MAP_WEIGHTING.put(9, 0);
        VIN_MAP_WEIGHTING.put(10, 9);
        VIN_MAP_WEIGHTING.put(11, 8);
        VIN_MAP_WEIGHTING.put(12, 7);
        VIN_MAP_WEIGHTING.put(13, 6);
        VIN_MAP_WEIGHTING.put(14, 5);
        VIN_MAP_WEIGHTING.put(15, 4);
        VIN_MAP_WEIGHTING.put(16, 3);
        VIN_MAP_WEIGHTING.put(17, 2);

        VIN_MAP_VALUE.put('0', 0);
        VIN_MAP_VALUE.put('1', 1);
        VIN_MAP_VALUE.put('2', 2);
        VIN_MAP_VALUE.put('3', 3);
        VIN_MAP_VALUE.put('4', 4);
        VIN_MAP_VALUE.put('5', 5);
        VIN_MAP_VALUE.put('6', 6);
        VIN_MAP_VALUE.put('7', 7);
        VIN_MAP_VALUE.put('8', 8);
        VIN_MAP_VALUE.put('9', 9);
        VIN_MAP_VALUE.put('A', 1);
        VIN_MAP_VALUE.put('B', 2);
        VIN_MAP_VALUE.put('C', 3);
        VIN_MAP_VALUE.put('D', 4);
        VIN_MAP_VALUE.put('E', 5);
        VIN_MAP_VALUE.put('F', 6);
        VIN_MAP_VALUE.put('G', 7);
        VIN_MAP_VALUE.put('H', 8);
        VIN_MAP_VALUE.put('J', 1);
        VIN_MAP_VALUE.put('K', 2);
        VIN_MAP_VALUE.put('M', 4);
        VIN_MAP_VALUE.put('L', 3);
        VIN_MAP_VALUE.put('N', 5);
        VIN_MAP_VALUE.put('P', 7);
        VIN_MAP_VALUE.put('R', 9);
        VIN_MAP_VALUE.put('S', 2);
        VIN_MAP_VALUE.put('T', 3);
        VIN_MAP_VALUE.put('U', 4);
        VIN_MAP_VALUE.put('V', 5);
        VIN_MAP_VALUE.put('W', 6);
        VIN_MAP_VALUE.put('X', 7);
        VIN_MAP_VALUE.put('Y', 8);
        VIN_MAP_VALUE.put('Z', 9);
    }

}
