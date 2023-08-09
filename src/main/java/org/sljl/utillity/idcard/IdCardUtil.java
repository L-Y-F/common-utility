package org.sljl.utillity.idcard;

import org.sljl.utillity.basic.StrUtil;

import java.util.regex.Pattern;

/**
 * 身份证号码校验（支持15位和18位校验）
 *
 * @author L.Y.F
 * @date 2022/3/4 1:18 下午
 */
public class IdCardUtil {

    // 每位加权因子
    private static int power[] = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    // 第18位校检码
    private static String verifyCode[] = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};


    private static final String reg18Bitb = "\\d{6}(18|19|([2]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
    private static final String reg15Bitb = "\\d{6}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}";

    public static boolean check18(String idCard) {
        if (StrUtil.isBlank(idCard) || idCard.length() != 18) {
            return false;
        }
        Pattern regexp = Pattern.compile(reg18Bitb);
        if (regexp.matcher(idCard).matches()) {
            return validate18Idcard(idCard);
        } else {
            return false;
        }
    }

    public static boolean check15(String idCard) {
        if (StrUtil.isBlank(idCard) || idCard.length() != 15) {
            return false;
        }
        Pattern regexp = Pattern.compile(reg15Bitb);
        if (regexp.matcher(idCard).matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkIdCard(String idCard) {
        if (StrUtil.isNotBlank(idCard)) {
            if (check18(idCard) || check15(idCard)) {
                return true;
            }
        }
        return false;
    }

    private static boolean validate18Idcard(String idCard) {
        // 获取前17位
        String idcard17 = idCard.substring(0, 17);
        // 获取第18位
        String idcard18Code = idCard.substring(17, 18);
        int[] IDnums = new int[17];
        for (int i = 0; i < idcard17.length(); i++) {
            IDnums[i] = idcard17.charAt(i) - '0';
        }
        int sum = getSum(IDnums, power);
        int last = getLastNum(sum);
        String lastStr = (last == 10 ? "X" : last+"");
        if (!idcard18Code.equalsIgnoreCase(lastStr)) {
            return false;
        }
        return true;
    }

    private static int getSum(int IDnums[], int coe[]) {
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum += IDnums[i] * coe[i];
        }
        return sum;
    }

    private static int getLastNum(int num) {
        num = num % 11;
        switch (num) {
            case 0:
                num = 1;
                break;
            case 1:
                num = 0;
                break;
            case 2:
                num = 10;
                break;
            case 3:
                num = 9;
                break;
            case 4:
                num = 8;
                break;
            case 5:
                num = 7;
                break;
            case 6:
                num = 6;
                break;
            case 7:
                num = 5;
                break;
            case 8:
                num = 4;
                break;
            case 9:
                num = 3;
                break;
            case 10:
                num = 2;
                break;
        }
        return num;
    }

}
