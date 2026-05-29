package com.sqq.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("cookie")
public class ResponseCookieController {
    private Logger logger = LoggerFactory.getLogger(ResponseCookieController.class);


    @PostMapping("addCookie")
    public Object addCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie cookie = new Cookie("cookieName", "cookieValue");

        cookie.setMaxAge(3600); // 设置Cookie的过期时间为1小时
        cookie.setPath("/"); // 设置Cookie的路径为根目录
        response.addCookie(cookie);
        response.getWriter().write("Setting cookies successfully!");


        return "添加成功";
    }


}
