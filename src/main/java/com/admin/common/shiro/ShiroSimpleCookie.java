package com.admin.common.shiro;

import org.apache.shiro.web.servlet.SimpleCookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShiroSimpleCookie extends SimpleCookie {


    @Override
    public String readValue(HttpServletRequest request, HttpServletResponse ignored) {
        return super.readValue(request, ignored);
    }


}
