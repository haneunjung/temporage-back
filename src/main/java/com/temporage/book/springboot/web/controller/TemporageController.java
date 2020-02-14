package com.temporage.book.springboot.web.controller;

import com.temporage.book.springboot.domain.posts.*;
import com.temporage.book.springboot.web.controller.dto.UserInfoDto;
import org.json.simple.JSONObject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@SuppressWarnings("unchecked")
public class TemporageController {

    @Autowired
    TemporageDataRepository appDataRepository;
    @Autowired
    UserInfoRepository userDataRepository;
    @Autowired
    TemporageSessionRepository sessionRepository;

    @PostMapping("/sign-up")
    public ResponseEntity<HttpStatus> userSignUp(@RequestBody UserInfoDto userInfoDto) {
        if(userDataRepository.findByEmail(userInfoDto.getEmail()) == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 이메일 중복

        UserInfo userData = new UserInfo(userInfoDto.getEmail(),
                BCrypt.hashpw(userInfoDto.getPassword(), BCrypt.gensalt()),
                userInfoDto.getName());

        userDataRepository.save(userData);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/sign-in") //RequestBody로 바꿀것.
    public ResponseEntity<HttpStatus> userSignIn(@RequestBody UserInfoDto userInfoDto, HttpServletRequest request) {
        UserInfo userInfo = userDataRepository.findByEmail(userInfoDto.getEmail());

        if (BCrypt.checkpw(userInfoDto.getPassword(), userInfo.getPassword())) {
            HttpSession session = request.getSession();

            TemporageSession session_data = new TemporageSession(session.getId(), userInfo.getSessionId()); //SESSION Part. 02/07
            sessionRepository.save(session_data);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    //SESSION Part. 02/07
    //TODO: JSESSIONID는 권한 체크할때 사용. JSESSIONID의 유무는 로그인 여부를 확인
    @PostMapping("/session-check")
    public JSONObject userSessionCheck(@RequestParam("email") String email, @CookieValue("JSESSIONID") String cSessionId) {
        JSONObject result = new JSONObject();
        TemporageSession sessionInfo = sessionRepository.findByEmail(email);

        if (cSessionId.equals(sessionInfo.getSessionId())) {
            result.put("result", 1);
            result.put("message", "Session ID is correct");
            return result;
        } else {
            result.put("result", 4);
            result.put("message", "SESSION ID is not correct.");
            result.put("Client SessionID", cSessionId);
            result.put("Server SessionID", sessionInfo.getSessionId());
            return result;
        }
    }

}
