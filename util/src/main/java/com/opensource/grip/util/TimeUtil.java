package com.opensource.grip.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangmin
 */
public class TimeUtil {

    public static final String FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_MINUTE = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_HOUR = "yyyy-MM-dd HH";
    public static final String FORMAT_DAY = "yyyy-MM-dd";

    /**
     * 生成格式化时间，默认使用模板：yyyy-MM-dd
     *
     * @param date 日期时间对象
     * @return String 格式化后的时间信息
     */
    public static String getFormat(Date date) {
        return getFormat(date, FORMAT_DAY);
    }

    /**
     * 将一个时间日期格式化为指定格式
     *
     * @param date      时间
     * @param formatStr 格式字符串
     * @return String
     */
    public static String getFormat(Date date, String formatStr) {
        SimpleDateFormat sf = new SimpleDateFormat(formatStr);
        return sf.format(date);
    }

    /**
     * 字符串转date
     *
     * @param str    str
     * @param format 时间格式
     * @return Date
     */
    public static Date strToDate(String str, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 增加天
     *
     * @param date   时间
     * @param d      天数
     * @param format 格式，为空时按照默认转换
     * @return 新的时间
     */
    public static String addDay(Date date, int d, String format) {
        Date newDate = addDay(date, d);
        return format == null ? getFormat(newDate) : getFormat(newDate, format);
    }

    /**
     * 增加天
     *
     * @param date 时间
     * @param d    天数
     * @return 新的时间
     */
    public static Date addDay(Date date, int d) {
        long curr = date.getTime();
        curr += (long) d * 24 * 60 * 60 * 1000;
        return new Date(curr);
    }

    /**
     * 增加秒数
     *
     * @param date 时间
     * @param s    秒数
     * @return 新的时间
     */
    public static Date addSecond(Date date, int s) {
        long curr = date.getTime();
        curr += (long) s * 1000;
        return new Date(curr);
    }

    /**
     * 增加秒数
     *
     * @param date   时间
     * @param s      秒数
     * @param format 格式
     * @return 时间戳
     */
    public static Long addSecond(Date date, int s, String format) {
        Date newDate = addSecond(date, s);
        return dateToStamp(newDate, format);
    }


    /**
     * 将时间转为unix时间戳
     *
     * @param str 时间字符串
     * @return String 单位ms
     */
    public static String dateToStamp(String str) {
        return dateToStamp(str, FORMAT_SECOND);
    }

    /**
     * 将时间转换为时间戳
     *
     * @param date   时间
     * @param format 格式
     * @return long 时间戳
     */
    public static Long dateToStamp(Date date, String format) {
        String s = getFormat(date, format);
        return Long.parseLong(dateToStamp(s, format));
    }

    /**
     * 将时间转为unix时间戳
     *
     * @param str    时间字符串
     * @param format 格式
     * @return str 时间戳
     */
    public static String dateToStamp(String str, String format) {
        String res = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date;
        try {
            date = simpleDateFormat.parse(str);
            long ts = date.getTime();
            res = String.valueOf(ts);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 将unix时间戳转为时间
     *
     * @param stamp  时间戳
     * @param format 时间格式
     * @return String
     */
    public static String stampToDate(String stamp, String format) {
        return stampToDate(new Long(stamp), format);
    }

    /**
     * 将unix时间戳转为时间
     *
     * @param stamp  时间戳
     * @param format 时间格式
     * @return String
     */
    public static String stampToDate(long stamp, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = new Date(stamp);
        return simpleDateFormat.format(date);
    }

    /**
     * 将unix时间戳转为时间
     *
     * @param stamp 时间戳
     * @return 时间
     */
    public static Date stampToDate(String stamp) {
        long lt = new Long(stamp);
        return new Date(lt);
    }

    /**
     * 格林威治转北京
     *
     * @param str 格林位置时间
     * @return string
     */
    public static String greenwichToBeijing(String str) {
        String stamp = dateToStamp(str, "yyyy-MM-dd'T'HH:mm:ss.SSS'+'SSSS");
        Date date = stampToDate(stamp);
        Date addSecond = addSecond(date, 8 * 60 * 60);
        return getFormat(addSecond, FORMAT_SECOND);
    }

    /**
     * 获取当前时间格式
     *
     * @return 时间格式
     */
    public static String getNowDateFormat() {
        String time = TimeUtil.getFormat(new Date(), "MM-dd-HH");
        String[] split = time.split("-");
        String s = split[2].compareTo("11") > 0 ? "PM" : "AM";
        return split[0] + "月" + split[1] + "日" + " " + s;
    }
}
