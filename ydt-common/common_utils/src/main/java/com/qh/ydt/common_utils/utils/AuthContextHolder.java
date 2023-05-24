package com.qh.ydt.common_utils.utils;


import com.qh.ydt.common_utils.helper.JwtHelper;

import javax.servlet.http.HttpServletRequest;


/**
 * 获取当前用户信息工具类
 *
 * @author 清欢
 * @date 2023/05/23  17:09:28
 *///获取当前用户信息工具类
public class AuthContextHolder {
    //获取当前用户id
    public static Long getUserId(HttpServletRequest request) {
        //从header获取token
        String token = request.getHeader("token");
        //jwt从token获取userid
        Long userId = JwtHelper.getUserId(token);
        return userId;
    }

    //获取当前用户名称
    public static String getUserName(HttpServletRequest request) {
        //从header获取token
        String token = request.getHeader("token");
        //jwt从token获取userid
        String userName = JwtHelper.getUserName(token);
        return userName;
    }
}