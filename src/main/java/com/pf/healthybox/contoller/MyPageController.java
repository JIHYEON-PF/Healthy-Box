package com.pf.healthybox.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class MyPageController {

    @GetMapping("/mypage")
    public String showMyPage() {
        return "userTemplates/myPage";
    }

}
