package org.sljl.utillity.time;


import org.sljl.utillity.basic.StrUtil;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * 系统通用的时间工具类
 *
 * @author L.Y.F
 */
public class TimeUtil {

    /**
     * 秒（最小单位为秒，如果需要使用毫秒请自己乘以1000）
     */
    public static final int SECOND = 1;
    /**
     * 分（最小单位为秒，如果需要使用毫秒请自己乘以1000）
     */
    public static final int MINUTE = SECOND * 60;
    /**
     * 时（最小单位为秒，如果需要使用毫秒请自己乘以1000）
     */
    public static final int HOUR = MINUTE * 60;
    /**
     * 天（最小单位为秒，如果需要使用毫秒请自己乘以1000）
     */
    public static final int DAY = HOUR * 24;
    /**
     * 周（最小单位为秒，如果需要使用毫秒请自己乘以1000）
     */
    public static final int WEEK = DAY * 7;
    /**
     * 月--按照通用的30天计算，如需特殊值请另行计算（最小单位为秒，如果需要使用毫秒请自己乘以1000）
     */
    public static final int MONTH_30 = DAY * 30;
    /**
     * 年--按照通用的365天计算，如需特殊值请另行计算（最小单位为秒，如果需要使用毫秒请自己乘以1000）
     */
    public static final int YEAR_365 = DAY * 365;

    private final static int[] dayArr = new int[]{20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22};
    private final static int[] constellationArr = new int[]{10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private final static int[] zodiacArr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    /**
     * 获取当月最后一天23:59:59的时间戳值，如需UnixTime请自行 / 1000
     *
     * @return
     */
    @Deprecated
    public static long getMonthLastDayForTimeStamp() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime().getTime();
    }

    /**
     * 当dayOfWeek==0时，获取指定日期所在周的周一对应的日期；
     * 在此基础上通过addDayOfWeek参数来灵活获取相关周的任意周几的日期；
     * 其思路就是先拿到指定日期所在周的周一对应日期，然后通过增减天数来获取上周，本周甚至任意周的任意周几日期；
     * 例如：
     * <pre>
     *     1. 获取当前日期所在周的周一日期：getDayOfWeekForDate(new Date(), 0);
     *     2. 获取当前日期所在周的周三日期；getDayOfWeekForDate(new Date(), 2);
     *     3. 获取当前日期所在周的周日日期；getDayOfWeekForDate(new Date(), 6);
     *     4. 获取当前日期的前一周的周一日期：getDayOfWeekForDate(new Date(), -7);
     *     5. 获取当前日期的前一周的周日日期：getDayOfWeekForDate(new Date(), -1);
     *     6. 获取当前日期的未来一周的周一日期：getDayOfWeekForDate(new Date(), 7);
     * </pre>
     *
     * @param date：指定日期
     * @param addDayOfWeek：指定日期所在周周一的基础上加几天或者减几天的日期
     *
     * @return
     */
    public static Date getDayOfWeekForDate(Date date, int addDayOfWeek) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, addDayOfWeek);
        return cal.getTime();
    }

    /**
     * 非标出生日期格式化方法，转换后统一使用标准格式yyyy-mm-dd
     *
     * @param sourceBirthday：原非标出生日期；该非标日期要求必须具备年、月、日，缺一不可； <pre>
     * <ul>
     * 例：
     * 	<li>1989年-----------------false</li>
     * 	<li>2月26日-----------------false</li>
     * 	<li>1980年2月1日-------------true</li>
     * 	<li>1988.02.01-------------true</li>
     * 	<li>1990/03/3--------------true</li>
     * </ul>
     * </pre>
     *
     * @return
     *
     * @throws Exception
     */
    @Deprecated
    public static String convertBirthday(String sourceBirthday) throws Exception {
        String targetBirthday = null;
        if (StrUtil.isNotBlank(sourceBirthday)) {
            sourceBirthday = sourceBirthday.replaceAll(" ", "");
            sourceBirthday = sourceBirthday.replaceAll("[生出]", "");
            sourceBirthday = sourceBirthday.replaceAll("号", "日");
            if (sourceBirthday.matches("(\\d{4})[年](\\d{2}|\\d{1})[月](\\d{2}|\\d{1})[日]")) {
                targetBirthday = date2String(string2LocalDate(sourceBirthday, "yyyy年m月d日"), "yyyy-mm-dd");
            } else if (sourceBirthday.matches("(\\d{4})[.](\\d{2}|\\d{1})[.](\\d{2}|\\d{1})")) {
                targetBirthday = date2String(string2LocalDate(sourceBirthday, "yyyy.m.d"), "yyyy-mm-dd");
            } else if (sourceBirthday.matches("(\\d{4})[/](\\d{2}|\\d{1})[/](\\d{2}|\\d{1})")) {
                targetBirthday = date2String(string2LocalDate(sourceBirthday, "yyyy/m/d"), "yyyy-mm-dd");
            } else if (sourceBirthday.matches("(\\d{4})[-](\\d{2}|\\d{1})[-](\\d{2}|\\d{1})")) {
                targetBirthday = date2String(string2LocalDate(sourceBirthday, "yyyy-m-d"), "yyyy-mm-dd");
            } else if (sourceBirthday.matches("(\\d{4})[年](\\d{2}|\\d{1})[月](\\d{2}|\\d{1})")) {
                targetBirthday = date2String(string2LocalDate(sourceBirthday, "yyyy年m月d"), "yyyy-mm-dd");
            } else if (sourceBirthday.matches("\\d{8}")) {
                targetBirthday = date2String(string2LocalDate(sourceBirthday, "yyyymmdd"), "yyyy-mm-dd");
            }
        }
        return targetBirthday;
    }

    /**
     * 获取本月是哪一月
     *
     * @return
     */
    @Deprecated
    public static int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }

    /**
     * 获取某年某月到某年某月按天的切片日期集合（间隔天数的集合）
     *
     * @param beginYear
     * @param beginMonth
     * @param endYear
     * @param endMonth
     * @param k
     *
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List getTimeList(int beginYear, int beginMonth, int endYear, int endMonth, int k) {
        List list = new ArrayList();
        if (beginYear == endYear) {
            for (int j = beginMonth; j <= endMonth; j++) {
                list.add(getTimeList(beginYear, j, k));
            }
        } else {
            {
                for (int j = beginMonth; j < 12; j++) {
                    list.add(getTimeList(beginYear, j, k));
                }
                for (int i = beginYear + 1; i < endYear; i++) {
                    for (int j = 0; j < 12; j++) {
                        list.add(getTimeList(i, j, k));
                    }
                }
                for (int j = 0; j <= endMonth; j++) {
                    list.add(getTimeList(endYear, j, k));
                }
            }
        }
        return list;
    }

    /**
     * 获取某年某月按天切片日期集合（某个月间隔多少天的日期集合）
     *
     * @param beginYear
     * @param beginMonth
     * @param k
     *
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static List getTimeList(int beginYear, int beginMonth, int k) {
        List list = new ArrayList();
        Calendar begincal = new GregorianCalendar(beginYear, beginMonth, 1);
        int max = begincal.getActualMaximum(Calendar.DATE);
        for (int i = 1; i < max; i = i + k) {
            list.add(begincal.getTime());
            begincal.add(Calendar.DATE, k);
        }
        begincal = new GregorianCalendar(beginYear, beginMonth, max);
        list.add(begincal.getTime());
        return list;
    }

    /**
     * 计算两个日期相差/相隔的天数
     * 相差：date = "yyyy-MM-dd HH:mm:ss"
     * 相隔：date = "yyyy-MM-dd";
     *
     * @param date
     * @param otherDate
     *
     * @return
     */
    @Deprecated
    public static long calculateTwoDayDifferences(Date date, Date otherDate) {
        return (date.getTime() - otherDate.getTime()) / (DAY * 1000);
    }

    /**
     * 返回当前日期是周几
     *
     * @param date
     * @return int
     */
    @Deprecated
    public static int getDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * 获取指定日期所在月的实际最大天数
     *
     * @param date
     * @return int
     */
    @Deprecated
    public static int getMonthForDays(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DATE);
    }

    //=============Java8新特性==================================================================
    /**
     * 日期类型转换（Date转换成Java8的LocalDateTime）
     *
     * @param date
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * 日期类型转换（Java8的LocalDateTime转换成Date）
     *
     * @param dateTime
     * @return java.util.Date
     */
    public static Date localDateTime2date(LocalDateTime dateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = dateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 将日期按照format格式转换成字符串
     *
     * @param date
     * @param format
     *
     * @return
     */
    public static String date2String(LocalDate date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }

    /**
     * 将日期按照format格式转换成字符串
     *
     * @param time
     * @param format
     *
     * @return
     */
    public static String date2String(LocalTime time, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return time.format(formatter);
    }

    /**
     * 将日期按照format格式转换成字符串
     *
     * @param dateTime
     * @param format
     *
     * @return
     */
    public static String date2String(LocalDateTime dateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return dateTime.format(formatter);
    }

    /**
     * 将字符串按照format格式转换成日期类型
     *
     * @param date
     * @param format
     *
     * @return
     */
    public static LocalDate string2LocalDate(String date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(date, formatter);
    }

    /**
     * 将字符串按照format格式转换成日期类型
     *
     * @param date
     * @param format
     *
     * @return
     */
    public static LocalDateTime string2LocalDateTime(String date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(date, formatter);
    }

    /**
     * 获取指定日期的开始时间
     *
     * @param localDate
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime getStartTimeOfDay(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MIN);
    }

    /**
     * 获取指定日期的结束时间
     *
     * @param localDate
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime getEndTimeOfDay(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MAX);
    }

    /**
     * 获取指定日期所在周的周一 日期
     *
     * @param localDate
     * @return java.time.LocalDate
     */
    public static LocalDate getFirstDayOfWeek(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    }

    /**
     * 获取指定日期所在周的周日 日期
     *
     * @param localDate
     * @return java.time.LocalDate
     */
    public static LocalDate getLastDayOfWeek(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
    }

    /**
     * 获取指定日期所在月的月初日期
     *
     * @param localDate
     * @return java.time.LocalDate
     */
    public static LocalDate getFirstDayOfMonth(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取指定日期所在月的月末日期
     *
     * @param localDate
     * @return java.time.LocalDate
     */
    public static LocalDate getLastDayOfMonth(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获取指定日期所在年的年初日期
     *
     * @param localDate
     * @return java.time.LocalDate
     */
    public static LocalDate getFirstDayOfYear(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.firstDayOfYear());
    }

    /**
     * 获取指定日期所在年的年末日期
     *
     * @param localDate
     * @return java.time.LocalDate
     */
    public static LocalDate getLastDayOfYear(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.lastDayOfYear());
    }

    /**
     * 获取指定时间的毫秒值
     *
     * @author L.Y.F
     * @date 2020/3/25
     * @param  * @param localDateTime
     * @return long
     */
    public static long getTimestamp(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 将时间戳转换成日期对象
     *
     * @param timestamp
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime timestamp2LocalDateTime(long timestamp) {
        return LocalDateTime.ofEpochSecond(timestamp/1000, 0, ZoneOffset.ofHours(8));
    }

    /**
     * 获取以指定日期为基准的前后N天
     *
     * @param date： 指定日期
     * @param days：days > 0：未来时间、days < 0：历史时间、days == 0：当前时间
     *
     * @return
     */
    public static LocalDate getBeforeOrAfterDay(LocalDate date, int days) {
        return date.plus(days, ChronoUnit.DAYS);
    }

    /**
     * 获取以指定时间为基准的前后N天
     *
     * @param dateTime： 指定日期
     * @param days：days > 0：未来时间、days < 0：历史时间、days == 0：当前时间
     *
     * @return
     */
    public static LocalDateTime getBeforeOrAfterDay(LocalDateTime dateTime, int days) {
        return dateTime.plus(days, ChronoUnit.DAYS);
    }

    /**
     * 获取指定日期所在月的总天数
     *
     * @param date
     * @return int
     */
    public static int getMaxDaysOfMonth(LocalDate date) {
        return getLastDayOfMonth(date).getDayOfMonth();
    }

    /**
     * 返回两个日期中较小的日期
     *
     * @param date1
     * @param date2
     * @return java.time.LocalDate
     */
    public static LocalDate getMin(LocalDate date1, LocalDate date2) {
        if (date1.isEqual(date2)) {
            return date1;
        } else {
            return date1.isBefore(date2) ? date1 : date2;
        }
    }

    /**
     * 返回两个日期中较小的日期
     *
     * @param date1
     * @param date2
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime getMin(LocalDateTime date1, LocalDateTime date2) {
        if (date1.isEqual(date2)) {
            return date1;
        } else {
            return date1.isBefore(date2) ? date1 : date2;
        }
    }

    /**
     * 返回两个日期中较大的日期
     *
     * @param date1
     * @param date2
     * @return java.time.LocalDate
     */
    public static LocalDate getMax(LocalDate date1, LocalDate date2) {
        if (date1.isEqual(date2)) {
            return date1;
        } else {
            return date1.isAfter(date2) ? date1 : date2;
        }
    }

    /**
     * 返回两个日期中较大的日期
     *
     * @param date1
     * @param date2
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime getMax(LocalDateTime date1, LocalDateTime date2) {
        if (date1.isEqual(date2)) {
            return date1;
        } else {
            return date1.isAfter(date2) ? date1 : date2;
        }
    }

    /**
     * 计算两个日期相差的天数
     * <pre>
     *     例如：2020-03-23和2020-03-25 返回2, 日期相同则返回0
     *
     * </pre>
     *
     * @param date1
     * @param date2
     * @return int
     */
    public static int calculateDifferenceBetweenDays(LocalDate date1, LocalDate date2) {
        return Math.abs((int)date1.until(date2, ChronoUnit.DAYS));
    }

    /**
     * 计算两个时间相差的毫秒数
     * <pre>
     *     例如：2020-03-25 16:25:25和2020-03-25 16:25:24 返回1000， 时间相同则返回0
     *
     * </pre>
     *
     * @param date1
     * @param date2
     * @return int
     */
    public static long calculateDifferenceBetweenMilliseconds(LocalDateTime date1, LocalDateTime date2) {
        return Math.abs(date1.until(date2, ChronoUnit.MILLIS));
    }

    /**
     * 非标出生日期格式化方法，转换后统一使用标准格式yyyy-mm-dd
     *
     * @param sourceBirthday：原非标出生日期；该非标日期要求必须具备年、月、日，缺一不可；
     * <pre>
     *     <ul>
     *      例：
     * 	    <li>1989年-----------------false</li>
     * 	    <li>2月26日-----------------false</li>
     * 	    <li>1980年2月1日-------------true</li>
     * 	    <li>1988.02.01-------------true</li>
     * 	    <li>1990/03/3--------------true</li>
     *      </ul>
     * </pre>
     *
     * @return
     */
    public static LocalDate convertBirthday2Date(String sourceBirthday) {
        if (StrUtil.isNotBlank(sourceBirthday)) {
            // 首先对生日进行预处理，去除无用的干扰项
            sourceBirthday = sourceBirthday.replaceAll(" ", "");
            sourceBirthday = sourceBirthday.replaceAll("[生出]", "");
            sourceBirthday = sourceBirthday.replaceAll("号", "日");
            if (sourceBirthday.matches("(\\d{4})[年](\\d{2}|\\d{1})[月](\\d{2}|\\d{1})[日]")) {
                return string2LocalDateTime(sourceBirthday, "yyyy年m月d日").toLocalDate();
            } else if (sourceBirthday.matches("(\\d{4})[.](\\d{2}|\\d{1})[.](\\d{2}|\\d{1})")) {
                return string2LocalDateTime(sourceBirthday, "yyyy.m.d").toLocalDate();
            } else if (sourceBirthday.matches("(\\d{4})[/](\\d{2}|\\d{1})[/](\\d{2}|\\d{1})")) {
                return string2LocalDateTime(sourceBirthday, "yyyy/m/d").toLocalDate();
            } else if (sourceBirthday.matches("(\\d{4})[-](\\d{2}|\\d{1})[-](\\d{2}|\\d{1})")) {
                return string2LocalDateTime(sourceBirthday, "yyyy-m-d").toLocalDate();
            } else if (sourceBirthday.matches("(\\d{4})[年](\\d{2}|\\d{1})[月](\\d{2}|\\d{1})")) {
                return string2LocalDateTime(sourceBirthday, "yyyy年m月d").toLocalDate();
            } else if (sourceBirthday.matches("\\d{8}")) {
                return string2LocalDateTime(sourceBirthday, "yyyymmdd").toLocalDate();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
