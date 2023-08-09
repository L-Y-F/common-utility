package org.sljl.utillity.enums;

/**
 * 教育程度枚举类
 *
 * @author L.Y.F
 * @create 2019-07-16 17:26
 */
public enum EducationEnum {

    EDU_UNKNOWN(-1, "未知"),
    /** 初中及以下 */
    EDU_TYPE_1(1, "初中及以下"),
    /** 高中及中专 */
    EDU_TYPE_2(2, "高中及中专"),
    /** 大专 */
    EDU_TYPE_3(3, "大专"),
    /** 本科 */
    EDU_TYPE_4(4, "本科"),
    /** 硕士 */
    EDU_TYPE_5(5, "硕士"),
    /** 博士 */
    EDU_TYPE_6(6, "博士"),
    ;

    // 全局项目唯一表示
    private Integer eduCode;
    // 全局项目名称
    private String eduName;

    private EducationEnum(Integer eduCode, String eduName) {
        this.eduCode = eduCode;
        this.eduName = eduName;
    }

    public Integer getEduCode() {
        return eduCode;
    }

    public String getEduName() {
        return eduName;
    }

    /**
     * 根据eduCode获取教育程度枚举类
     *
     * @param eduCode
     * @return
     */
    public static EducationEnum getEnum(Integer eduCode) {
        for (EducationEnum education : EducationEnum.values()) {
            if (education.eduCode.equals(eduCode)) {
                return education;
            }
        }
        return null;
    }

}
