package com.pf.healthybox.controller.apiController;

import com.pf.healthybox.domain.baseInformation.BiUser;
import com.pf.healthybox.dto.request.baseInformationReq.BiUserRequest;
import com.pf.healthybox.service.BiUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Slf4j
@RequestMapping("/api/user-manage")
@RestController
public class UserManageApiController {

    private final BiUserService biUserService;

    public UserManageApiController(BiUserService biUserService) {
        this.biUserService = biUserService;
    }

    // 회원가입 페이지 //
    //회원가입
    @PostMapping("/sign-up")
    public void signUp(@RequestBody BiUserRequest req) {
        biUserService.signUp(req.toDto());
    }

    //회원 탈퇴
    @Transactional
    @DeleteMapping("/delete-user")
    public void deleteUser(@RequestParam String userId, @RequestParam String userPw, HttpServletRequest request) {
        BiUser user = biUserService.showUserInfo(userId);
        if (user.getUserPw().equals(userPw)) {
            biUserService.deleteUser(userId, userPw);
            request.getSession().invalidate();
        }
    }

    //ID 중복검사
    @GetMapping("/confirmId/{userId}")
    public Boolean confirmId(@PathVariable String userId) {
        return biUserService.confirmId(userId);
    }


    // 로그인 페이지 //
    //로그인 기능
    @PostMapping("/login")
    public Boolean login(@RequestParam String userId, @RequestParam String userPw, HttpServletRequest request) {
        BiUser user =  biUserService.login(userId, userPw);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", user);
        }

        return (user != null);
    }

    //아이디 찾기
    @GetMapping("/find-id")
    public String findId(@RequestParam String userName, @RequestParam String email) {
        return biUserService.findUserIdByNameAndEmail(userName, email);
    }

    //비밀번호 찾기
    @GetMapping("/find-password")
    public Boolean findPassword(@RequestParam String userId, @RequestParam String name, @RequestParam String email) {
        return biUserService.findPassword(userId, name, email);
    }


}
