package org.sljl.utillity.enums;

/**
 * 性别枚举类
 *
 * @author L.Y.F
 * @create 2019-07-17 11:41
 */
public enum SexTypeEnum {

    /** 性别-未知 */
    UNKNOWN(-1, "未知"),
    /** 性别-男 */
    MALE(1, "男"),
    /** 性别-女 */
    FEMALE(2, "女"),
    ;


    private Integer code;
    private String name;

    private SexTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static SexTypeEnum getEnum(Integer code) {
        for (SexTypeEnum sexType : SexTypeEnum.values()) {
            if (sexType.code.equals(code)) {
                return sexType;
            }
        }
        return SexTypeEnum.UNKNOWN;
    }

}
