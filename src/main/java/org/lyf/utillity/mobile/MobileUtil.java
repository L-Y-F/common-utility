package org.lyf.utillity.mobile;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * 手机号校验工具类
 * *注意：运营商开启携号转网功能后，此此处分运营商校验规则不在适用，应适当调整
 * 
 * @author L.Y.F
 *
 */
public class MobileUtil {
	
	/**
	 * 中国电信号码格式验证 手机段： 133,149,153,173,177,180,181,189,193,199,1349,1410,1700,1701,1702
	 * 
	 **/
	private static final String CHINA_TELECOM_PATTERN = "(?:^(?:\\+86)?1(?:33|49|53|7[37]|8[019]|9[39])\\d{8}$)|(?:^(?:\\+86)?1349\\d{7}$)|(?:^(?:\\+86)?1410\\d{7}$)|(?:^(?:\\+86)?170[0-2]\\d{7}$)";
	
	/**
	 * 中国联通号码格式验证 手机段：130,131,132,145,146,155,156,166,171,175,176,185,186,1704,1707,1708,1709
	 * 
	 **/
	private static final String CHINA_UNICOM_PATTERN = "(?:^(?:\\+86)?1(?:3[0-2]|4[56]|5[56]|6[67]|7[156]|8[56])\\d{8}$)|(?:^(?:\\+86)?170[47-9]\\d{7}$)";
	
	/**
	 * 中国移动号码格式验证
	 * 手机段：134,135,136,137,138,139,147,148,150,151,152,157,158,159,178,182,183,184,187,188,191,192,194,195,196,197,198,1440,1703,1705,1706
	 * 
	 **/
	private static final String CHINA_MOBILE_PATTERN = "(?:^(?:\\+86)?1(?:3[4-9]|4[78]|5[0-27-9]|7[28]|8[2-478]|9[124-8])\\d{8}$)|(?:^(?:\\+86)?1440\\d{7}$)|(?:^(?:\\+86)?170[356]\\d{7}$)";

	/**
	 * 中国大陆手机号码校验
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean checkPhone(String phone) {
		if (StringUtils.isNotBlank(phone)) {
			if (checkChinaMobile(phone) || checkChinaUnicom(phone) || checkChinaTelecom(phone)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 中国移动手机号码校验
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean checkChinaMobile(String phone) {
		if (StringUtils.isNotBlank(phone)) {
			Pattern regexp = Pattern.compile(CHINA_MOBILE_PATTERN);
			if (regexp.matcher(phone).matches()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 中国联通手机号码校验
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean checkChinaUnicom(String phone) {
		if (StringUtils.isNotBlank(phone)) {
			Pattern regexp = Pattern.compile(CHINA_UNICOM_PATTERN);
			if (regexp.matcher(phone).matches()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 中国电信手机号码校验
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean checkChinaTelecom(String phone) {
		if (StringUtils.isNotBlank(phone)) {
			Pattern regexp = Pattern.compile(CHINA_TELECOM_PATTERN);
			if (regexp.matcher(phone).matches()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 隐藏手机号码中间四位
	 *
	 * @param phone
	 * @return java.lang.String
	 */
	public static String hideFourMiddleNumber(String phone) {
		if (StringUtils.isBlank(phone)) {
			return phone;
		}
		return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
	}
	
}
