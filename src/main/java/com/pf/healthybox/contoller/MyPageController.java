package com.pf.healthybox.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/mypage")
@Controller
public class MyPageController { //마이페이지 관련 페이지에 대한 컨트롤러(주문내역, 장바구니, 배송정보 관리 등)

    @GetMapping("/") //TODO: @PathVariable 을 통해 {id}를 입력받아 해당 아이디의 마이페이지 출력하게 변경
    public String showMyPage() {
        return "userTemplates/myPage";
    }

}
