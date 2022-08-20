package com.pf.healthybox.contoller.apiController;

import com.pf.healthybox.domain.baseInformation.BiUser;
import com.pf.healthybox.dto.baseInformationDto.BiUserDto;
import com.pf.healthybox.dto.request.baseInformationReq.BiUserRequest;
import com.pf.healthybox.dto.response.baseInformationRes.BiUserResponse;
import com.pf.healthybox.service.BiUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RequestMapping("/api/user-manage")
@RestController
public class UserManageApiController {

    private final BiUserService biUserService;

    public UserManageApiController(BiUserService biUserService) {
        this.biUserService = biUserService;
    }

    //회원가입
    @PostMapping("/sign-up")
    public void signUp(@RequestBody BiUserRequest req) {
        biUserService.signUp(req.toDto());
    }

    //회원 탈퇴
    @DeleteMapping("/delete-user")
    public void deleteUser(@RequestParam String userId, @RequestParam String userPw) {
        BiUserResponse user = biUserService.showUserInfo(userId);
        if (user.userPw().equals(userPw)) {
            biUserService.deleteUser(userId, userPw);
        }
    }


    //ID 중복검사
    @GetMapping("/confirmId/{userId}")
    public Boolean confirmId(@PathVariable String userId) {
        return biUserService.confirmId(userId);
    }


}
