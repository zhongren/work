package com.admin.common.util;

import com.admin.common.exception.BaseException;
import org.apache.commons.lang.StringUtils;

/**
 * Created by zhongr on 2017/7/6.
 */
public class StringUtil {
    public static String join(String[] strs) {
        return String.join(",", strs);
    }

    public static Integer getInteger(String str) {
        try {
            if (StringUtil.isEmpty(str)) {
                return null;
            }
            return Integer.valueOf(str);
        } catch (NumberFormatException e) {
            throw new BaseException(e, "数据类型转换错误");
        }
    }

    public static Boolean getBoolean(String str) {
        try {
            if (StringUtil.isEmpty(str)) {
                return null;
            }
            return Boolean.valueOf(str);
        } catch (NumberFormatException e) {
            throw new BaseException(e, "数据类型转换错误");
        }
    }

    public static boolean isEmpty(String str) {
        return StringUtils.isBlank(str);
    }

    /**
     * 获取删除后缀的string
     *
     * @param str
     * @param suffix
     * @return
     */
    public static String dealSuffix(String str, String suffix) {
        if (isEmpty(str)) {
            return null;
        }
        str = str.substring(0, str.lastIndexOf(suffix));
        return str;
    }

    /**
     * 驼峰转换  大写转下划线
     *
     * @param str
     * @return
     */
    public static String camelToUnderline(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char tmp = str.charAt(i);
            if (Character.isUpperCase(tmp)) {
                sb.append("_");
                sb.append(Character.toLowerCase(tmp));
            } else {
                sb.append(tmp);
            }
        }
        return sb.toString();
    }

    /**
     * 驼峰转换  下划线转大写
     *
     * @param str
     * @return
     */
    public static String underlineToCamel(String str) {
        int index = str.indexOf("_");
        if (index == -1) {
            return str;
        } else {
            String str1 = str.substring(0, index);
            String str2 = str.substring(index + 1, str.length());
            String str3 = str2.substring(0, 1).toUpperCase() + str2.substring(1, str2.length());
            str = str1 + str3;
            return underlineToCamel(str);
        }
    }

    public static void main(String[] args) {


        System.out.println(underlineToCamel("user_name_temp"));
        System.out.println(camelToUnderline("userName"));
    }
}
