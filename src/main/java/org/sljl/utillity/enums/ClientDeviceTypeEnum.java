package org.sljl.utillity.enums;

public enum ClientDeviceTypeEnum {

    /** IOS */
    IOS(1, "IOS"),
    /** ANDROID */
    ANDROID(2, "ANDROID"),
    /** PC */
    PC(3, "PC"),
    /** H5 */
    H5(4, "H5"),
    /** MINIPROGRAM */
    MINIPROGRAM(5, "MINIPROGRAM"),

    ;

    private Integer code;
    private String name;

    private ClientDeviceTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static ClientDeviceTypeEnum getEnum(Integer code) {
        for (ClientDeviceTypeEnum deviceType : ClientDeviceTypeEnum.values()) {
            if (deviceType.code.equals(code)) {
                return deviceType;
            }
        }
        return null;
    }

}
