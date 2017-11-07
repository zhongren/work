package com.admin.common.orm;

import java.util.List;

/**
 * 用在mybatis的xml中，进行反射调用
 * @author zhongren
 * @date 2017/11/7
 */
public class MybatisHelper {
    public static boolean isList(Object object) {
        if (object != null && object instanceof List) {
            return true;
        }
        return false;
    }
}
