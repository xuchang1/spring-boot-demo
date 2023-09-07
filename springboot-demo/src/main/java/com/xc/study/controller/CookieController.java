package com.xc.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class CookieController {

    @GetMapping("getCookies")
    public String getCookies(String id, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("1", "2");
        response.addCookie(cookie);
        return id;
    }
}
