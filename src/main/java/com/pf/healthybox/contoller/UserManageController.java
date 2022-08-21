package com.pf.healthybox.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/user")
@Controller
public class UserManageController { // 유저 관리에 대한 컨트롤러(회원가입, 탈퇴, 로그인, 로그아웃 등)

    @GetMapping("/signUp")
    public String showSignUp () {
        return "userTemplates/signUp";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "userTemplates/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        if (request.getSession() != null) {
            request.getSession().invalidate();
        }
        return "redirect:/";
    }

}
