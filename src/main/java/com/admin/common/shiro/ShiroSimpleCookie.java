package com.admin.common.shiro;

import com.admin.common.util.StringUtil;
import com.admin.common.util.WebUtil;
import org.apache.shiro.web.servlet.SimpleCookie;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShiroSimpleCookie extends SimpleCookie {
    public ShiroSimpleCookie( String cookieName ){
        super( cookieName ) ;
    }
    private String tokenName;

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    @Override
    public String readValue(HttpServletRequest request, HttpServletResponse ignored) {
        String tokenName=getTokenName();
        String tokenValue= WebUtil.getHeader(request,tokenName);
        if (StringUtil.isEmpty(tokenValue )) {
            return super.readValue(request, ignored);
        }
        return tokenValue;
    }


}
