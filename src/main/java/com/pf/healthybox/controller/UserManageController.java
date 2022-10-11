package com.pf.healthybox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@RequestMapping("/user")
@Controller
public class UserManageController { // 유저 관리에 대한 컨트롤러(회원가입, 탈퇴, 로그인, 로그아웃 등)

    @GetMapping("/signUp")
    public String showSignUp () {
        return "userTemplates/signUp";
    }

    @GetMapping("/login")
    public String showLogin(Model model, HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Arrays.stream(cookies).forEach(cookie -> {
                if (cookie.getName().equals("rememberIdCookie")) {
                    model.addAttribute("userId", cookie.getValue());
                }
            });
        }
        return "userTemplates/login";
    }

    @GetMapping("/login/{userId}")
    public String showLoginWithId(@PathVariable String userId, ModelMap model) {
        model.addAttribute("userId", userId);
        return "userTemplates/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        if (request.getSession() != null) {
            request.getSession().invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/find-id")
    public String showFindId() {
        return "userTemplates/findId";
    }

    @GetMapping("/find-pw")
    public String showFindPw() {
        return "userTemplates/findPw";
    }

}
