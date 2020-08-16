package cn.dreamchan.common.core.utils;

import com.google.common.base.CaseFormat;


/**
 * 字符串工具类
 *
 * @author DreamChan
 */
public class StringUtils {

    public static boolean isNotBlank(final String str) {
        return !isBlank(str);
    }

    public static boolean isBlank(final String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /**
     * 删除前后空格
     */
    public static String trim(final String str) {
        return str == null ? null : str.trim();
    }

    public static String capitalize(final String str) {
        return org.apache.commons.lang3.StringUtils.capitalize(str);
    }

    public static String uncapitalize(final String str) {
        return org.apache.commons.lang3.StringUtils.uncapitalize(str);
    }

    /**
     * 下划线的字符串转换为驼峰式字符串
     */
    public static String lowerUnderscoreToLowerCamel(String str) {
        CaseFormat fromFormat = CaseFormat.LOWER_UNDERSCORE;//
        CaseFormat toFormat = CaseFormat.LOWER_CAMEL;
        return fromFormat.to(toFormat, str);
    }

    /**
     * 驼峰式的字符串转换为下划线
     */
    public static String lowerCamelToLowerUnderscore(String str) {
        CaseFormat fromFormat = CaseFormat.LOWER_CAMEL;
        CaseFormat toFormat = CaseFormat.LOWER_UNDERSCORE;
        return fromFormat.to(toFormat, str);
    }

    /**
     * 字符串格式化
     * @param s
     * @param args
     * @return
     */
    public static String format(String s, Object... args) {
        if (isNotBlank(s)) {
            return String.format(s, args);
        }
        return "";
    }
}



