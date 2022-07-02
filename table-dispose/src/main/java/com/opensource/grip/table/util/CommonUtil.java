package com.opensource.grip.table.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangmin
 * @date 2020/7/27 14:17
 */
public class CommonUtil {
    private static final Pattern LINE_PATTERN = Pattern.compile("[_|-](\\w)");
    private static final Pattern HUMP_PATTERN = Pattern.compile("[A-Z]");
    private static final Pattern CHINESE = Pattern.compile("[\u4e00-\u9fa5]");
    private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    public static String replace(String str, String subChar) {
        int s = str.indexOf(subChar);
        return str.replace(str.substring(0, s + 1), "");
    }

    /**
     * 判断是否包含中文
     *
     * @param str str
     * @return result
     */
    public static boolean isContainChinese(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        Matcher m = CHINESE.matcher(str);
        return m.find();
    }

    /**
     * 时间格式判断
     *
     * @param date   实际日期
     * @param format 比较的日期格式
     * @return boolean
     */
    public static boolean isLegalDate(String date, String format) {
        if (StringUtils.isEmpty(date) || date.length() < format.length()) {
            return false;
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            Date date1 = dateFormat.parse(date);
            return date.equals(dateFormat.format(date1));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 结果展示
     *
     * @param value value
     * @param <T>   T
     */
    @SafeVarargs
    public static <T> void valueView(T... value) {
        Arrays.stream(value).forEach(e -> logger.info("value:【 {} 】", e));
    }


    public static String getStrField(JSONObject response, int index, String field) {
        String value = response.getJSONArray("list").getJSONObject(index).getString(field);
        return value == null ? "" : value;
    }

    public static Integer getIntField(JSONObject response, int index, String field) {
        return response.getJSONArray("list").getJSONObject(index).getInteger(field);
    }

    public static String getStrField(JSONArray jsonArray, int index, String field) {
        String value = jsonArray.getJSONObject(index).getString(field);
        return value == null ? "" : value;
    }

    public static List<String> getMoreParam(JSONObject object, String... paramName) {
        List<String> list = new ArrayList<>();
        Arrays.stream(paramName).forEach(e -> {
            if (StringUtils.isEmpty(e)) {
                throw new RuntimeException("param类型应为String类型且不能为空");
            } else {
                if (!object.containsKey(e)) {
                    throw new RuntimeException("object中不包含此key");
                }
                list.add(object.getString(e));
            }
        });
        return list;
    }

    /**
     * 取小数点后位数
     *
     * @param a     数值
     * @param digit 位数
     * @return double b
     */
    public static double getDecimal(double a, int digit) {
        int cardinal = (int) Math.pow(10, digit);
        return (double) Math.round(a * cardinal) / cardinal;
    }

    /**
     * 计算比值向上取整
     *
     * @param numerator   分子
     * @param denominator 分母
     * @return 取整结果
     */
    public static int getCeilIntRatio(int numerator, int denominator) {
        double c = (double) numerator / denominator;
        return (int) Math.ceil(c);
    }

    /**
     * 计算比值四舍五入取整
     *
     * @param numerator   分子
     * @param denominator 分母
     * @return 比值
     */
    public static int getRoundIntRatio(int numerator, int denominator) {
        double a = (double) numerator / denominator;
        return (int) Math.round(a);
    }

    /**
     * 计算比值四舍五入
     *
     * @param numerator   分子
     * @param denominator 分母
     * @param scale       保留小数点后位数
     * @return 比值
     */
    public static double getDoubleRatio(double numerator, double denominator, int scale) {
        return new BigDecimal(numerator / denominator).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 计算整数百分比
     *
     * @param numerator   分子
     * @param denominator 分母
     * @return 百分比
     */
    public static int getIntPercent(double numerator, double denominator) {
        return numerator == 0 || denominator == 0 ? 0 : (int) Math.ceil(numerator / denominator * 100);
    }

    /**
     * 特殊计算
     */
    public static String getPercent(double a, double b) {
        if (a == 0 && b == 0) {
            return "0.0%";
        }
        if (b == 0) {
            return "100.0%";
        } else {
            return getPercent(a, b, 4);
        }
    }

    /**
     * 获取百分比
     *
     * @param a     a
     * @param b     b
     * @param scale 保留小数点后位数
     * @return result
     */
    public static String getPercent(double a, double b, int scale) {
        if (b == 0) {
            return "0.0%";
        }
        double c = new BigDecimal(a / b).divide(new BigDecimal(1), scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        StringBuilder stringBuilder = new StringBuilder();
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);
        String str = nf.format(c);
        if ("0".equals(String.valueOf(str.charAt(str.length() - 2)))) {
            stringBuilder.append(str);
            stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length() - 1, "");
            String s = stringBuilder.toString();
            return getS(s);
        }
        return getS(str);
    }

    private static String getS(final String y) {
        return y.contains(",") ? y.replace(y.substring(y.indexOf(","), y.indexOf(",") + 1), "") : y;
    }

    public static String humpToLineReplaceFirst(String str) {
        Matcher matcher = HUMP_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString().replaceFirst("_", "");
    }

    /**
     * 驼峰转下划线
     *
     * @param str str
     * @return result
     */
    public static String humpToLine(String str) {
        Matcher matcher = HUMP_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     *
     * @param str              str
     * @param firstIsUpperCase 首字母是否大写
     * @return result
     */
    public static String lineToHump(String str, Boolean firstIsUpperCase) {
        str = str.toLowerCase();
        Matcher matcher = LINE_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        if (firstIsUpperCase) {
            String s = sb.toString().substring(0, 1).toUpperCase();
            return s + sb.toString().substring(1, sb.length());
        } else {
            return sb.toString();
        }
    }


    /**
     * 获取页面跳转页数
     * 当接口每页只能传入pageSize时，获取接口的访问次数来得到list.size()
     *
     * @param listSize list的尺寸
     * @param pageSize 接口要求的size
     * @return a
     */
    public static int getTurningPage(double listSize, double pageSize) {
        Preconditions.checkArgument(listSize >= 0, "listSize不可为负数");
        Preconditions.checkArgument(pageSize > 0, "pageSize不可为负数");
        double a = listSize > pageSize ? listSize % pageSize == 0 ? listSize / pageSize : Math.ceil(listSize / pageSize) + 1 : 2;
        return (int) a;
    }

    /**
     * 获取随机数
     *
     * @param digitNumber 位数
     * @return 结果
     */
    public static String getRandom(int digitNumber) {
        return digitNumber == 0 ? "" : String.valueOf((int) ((Math.random() * 9 + 1) * (Math.pow(10, digitNumber - 1))));
    }

    /**
     * 获取随机数
     *
     * @param initial  初始
     * @param eventual 结束
     * @return 结果
     */
    public static Integer getRandom(int initial, int eventual) {
        Random random = new Random();
        return random.nextInt(eventual - initial) + initial;
    }

    /**
     * 集合去重
     *
     * @param arr 集合
     * @return 一个新集合
     */
    private static List<String> removeDuplicates(List<String> arr) {
        List<String> list = new ArrayList<>();
        Iterator<String> it = arr.iterator();
        while (it.hasNext()) {
            String a = it.next();
            if (list.contains(a)) {
                it.remove();
            } else {
                list.add(a);
            }
        }
        return list;
    }

    /**
     * 判断字符串列表每一项均不为空，包括空字符串与null
     *
     * @param strList 字符串列表
     * @return boolean 有一个为空则返回false，全不为空返回true
     */
    public static boolean strListNotNull(String[] strList) {
        boolean notNull = true;
        for (String str : strList) {
            if (StringUtils.isEmpty(str)) {
                notNull = false;
                break;
            }
        }
        return notNull;
    }

    /**
     * 对比两个数组
     *
     * @param obj1 第一个数组
     * @param obj2 第二个数组
     * @return boolean 数量、内容和顺序都一致返回true，有任意一项不满足返回false
     */
    public static boolean arrayEquals(Object[] obj1, Object[] obj2) {
        if (obj1 == null && obj2 == null) {
            return true;
        }
        if (obj1 != null && obj1.length == 0 && obj2 != null && obj2.length == 0) {
            return true;
        }
        if (obj1 != null && obj2 != null && obj1.length == obj2.length) {
            for (int i = 0; i < obj1.length; i++) {
                if (!obj1[i].equals(obj2[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static String firstUpperCase(String str) {
        String first = str.substring(0, 1).toUpperCase();
        return first + str.substring(1);
    }
}
