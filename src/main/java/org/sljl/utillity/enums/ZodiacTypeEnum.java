package org.sljl.utillity.enums;


/**
 * 属相枚举类
 * 
 * @author L.Y.F
 *
 */
public enum ZodiacTypeEnum {
	
	/** 未知 */
	ZODIAC_UNKNOWN(-1, "未知"),
	/** 鼠 */
	ZODIAC_1(1, "鼠"),
	/** 牛 */
	ZODIAC_2(2, "牛"),
	/** 虎 */
	ZODIAC_3(3, "虎"),
	/** 兔 */
	ZODIAC_4(4, "兔"),
	/** 龙 */
	ZODIAC_5(5, "龙"),
	/** 蛇 */
	ZODIAC_6(6, "蛇"),
	/** 马 */
	ZODIAC_7(7, "马"),
	/** 羊 */
	ZODIAC_8(8, "羊"),
	/** 猴 */
	ZODIAC_9(9, "猴"),
	/** 鸡 */
	ZODIAC_10(10, "鸡"),
	/** 狗 */
	ZODIAC_11(11, "狗"),
	/** 猪 */
	ZODIAC_12(12, "猪"),
	;

	private Integer code;
	private String value;

	private ZodiacTypeEnum(Integer code, String value) {
		this.code = code;
		this.value = value;
	}

	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}
	
	/**
	 * 默认返回“未知属相”
	 * @param code
	 * @return
	 */
	public static ZodiacTypeEnum getEnum(Integer code) {
		for (ZodiacTypeEnum zodiacType : ZodiacTypeEnum.values()) {
			if (zodiacType.code == code) {
				return zodiacType;
			}
		}
		return ZODIAC_UNKNOWN;
	}
	
	/**
	 * 默认返回“未知属相”
	 * @param value
	 * @return
	 */
	public static ZodiacTypeEnum getEnum(String value) {
		for (ZodiacTypeEnum zodiacType : ZodiacTypeEnum.values()) {
			if (zodiacType.value.equals(value)) {
				return zodiacType;
			}
		}
		return ZODIAC_UNKNOWN;
	}
	
}
