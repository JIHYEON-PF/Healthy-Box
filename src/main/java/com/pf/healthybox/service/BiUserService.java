package com.pf.healthybox.service;

import com.pf.healthybox.domain.baseInformation.BiUser;
import com.pf.healthybox.dto.baseInformationDto.BiUserDto;
import com.pf.healthybox.repository.BiUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
public class BiUserService {

    private final BiUserRepository biUserRepository;


    public BiUserService(BiUserRepository biUserRepository) {
        this.biUserRepository = biUserRepository;
    }

    //회원가입 메서드
    public void signUp(BiUserDto dto) {
        String userId = dto.userId();

        Optional<BiUser> checkUser = biUserRepository.findById(userId);

        if (checkUser.isEmpty()) {
            BiUser insertUser = dto.toEntity();
            insertUser.setCompName("");
            insertUser.setRecoCode(insertRecoCode());
            insertUser.setIsDel("N");
            insertUser.setAuthDiv("USE");
            insertUser.setAuthLevel("USE");

            if (insertUser.getNickname().isBlank()) {
                insertUser.setNickname(insertUser.getUserName());
            }

            biUserRepository.save(insertUser);
        } else {
            throw new RuntimeException("UserInfo is exists");
        }

    }

    //회원 탈퇴 메서드
    public void deleteUser(String userId, String userPw) {
        biUserRepository.deleteByUserIdAndUserPw(userId, userPw);
    }

    //사용자 정보를 조회하는 메서드
    @Transactional
    public BiUser showUserInfo(String userId) {
        return biUserRepository.findById(userId)
                .orElse(null);
    }

    //회원가입 - ID 중복검사 메서드
    public Boolean confirmId(String userId) {
        return biUserRepository.existsByUserId(userId);
    }

    //추천인 등록 메서드
    public String insertRecoCode() {

        StringBuilder buf = new StringBuilder();

        // 추천코드 자동생성( 1~3자리 숫자는 알파벳 대문자로 구성, 4~8자리는 랜덤한 숫자로 구성되도록)
        while (true) {
            buf.setLength(0);

            for (int i = 0 ; i < 4 ; i++) {
                if (i <= 2) {
                    buf.append((char) ((int) (Math.random() * 26) + 65));
                } else {
                    buf.append(String.format("%05d", (int) (Math.random()*99999)+1));
                }
            }

            if (!biUserRepository.existsByRecoCode(buf.toString())) {
                return buf.toString();
            }
        }
    }

    public BiUser login(String userId, String userPw) {
        return biUserRepository.findById(userId)
                .filter((data) -> data.getUserPw().equals(userPw))
                .orElse(null);
    }

    @Transactional
    public void modifyUserInfo(BiUserDto dto) {
        BiUser findUser = biUserRepository.getReferenceById(dto.userId());

        if (!dto.userPw().isBlank()) {
            findUser.setUserPw(dto.userPw());
        }
        findUser.setPhoneNumber(dto.phoneNumber());
        findUser.setUserName(dto.userName());
        findUser.setNickname(dto.nickname());
        findUser.setEmail(dto.email());
        findUser.setZipcode(dto.zipcode());
        findUser.setAddress1(dto.address1());
        findUser.setAddress2(dto.address2());

        biUserRepository.save(findUser);
    }

    @Transactional
    public Boolean findPassword(String userId, String name, String email) {
        BiUser entity = biUserRepository.findByUserIdAndUserNameAndEmail(userId, name, email);

        if (entity == null) {
            return false;
        }

        //비밀번호 변경 로직 추가
        String tempPw = RandomStringUtils.randomAlphanumeric(10);
        entity.setUserPw(tempPw);

        //mail Server 설정
        String charSet = "utf-8";
        String hostSMTP = "smtp.naver.com";
        // TODO : 아이디, 비밀번호, SMTP PORT는 DB에서 조회해서 가져오도록 변경
        String hostSMTPId = "kenj_h";
        String hostSMTPPw = "WLGUS!!1203";

        //Sender
        String fromEmail = "kenj_h@naver.com";
        String fromName = "Healthy Box";

        //Mail Content
        String subject = name + "님의 임시 비밀번호가 발급되었습니다.";
        String msg = "";

        msg += "<div style='text-align: center; justify-content: center; align-items: center; width: 600px;'>";
        msg += "    <div>";
        msg += "        <img src='https://healthy-box.herokuapp.com/image/logo.jpg' alt='logo'>";
        msg += "    </div>";
        msg += "    <div style='background-color: rgba(84,146,91,0.2); padding: 5px;'>";
        msg += "        <div style='font-weight: bold; font-size: 20px; color: rgba(84,146,91,0.8); padding: 5px'>";
        msg += "            <span>회원님의 임시 비밀번호가 발급되었습니다.</span>";
        msg += "        </div>";
        msg += "        <hr style='background-color: white; border: 0px; height: 1px;'>";
        msg += "        <div>";
        msg += "            <p>";
        msg += "                <span>" + name + "회원님의 임시 비밀번호는</span>";
        msg += "                <span style='font-weight: bold; font-size: 15px;'>" + tempPw + "</span>";
        msg += "                <span>입니다.</span>";
        msg += "            </p>";
        msg += "            <p>";
        msg += "                <span style='font-weight: bold; color: red;'>임시비밀번호로 로그인 후 비밀번호 변경 후 사용부탁드립니다.</span>";
        msg += "            </p>";
        msg += "        </div>";
        msg += "    </div>";
        msg += "</div>";

        //Mail Receiver
        String receiver = email;

        try {
            HtmlEmail mail = new HtmlEmail();

            mail.setDebug(true);
            mail.setCharset(charSet);
            mail.setSSLOnConnect(true);
            mail.setHostName(hostSMTP);
            mail.setSmtpPort(587);
            mail.setAuthentication(hostSMTPId, hostSMTPPw);
            mail.setStartTLSEnabled(false);
            mail.addTo(receiver);
            mail.setFrom(fromEmail, fromName);
            mail.setSubject(subject);
            mail.setHtmlMsg(msg);
            mail.send();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String findUserIdByNameAndEmail(String userName, String email) {
        return (biUserRepository.findUserIdByUserNameAndEmail(userName, email) == null ? "" : biUserRepository.findUserIdByUserNameAndEmail(userName, email));
    }
}
