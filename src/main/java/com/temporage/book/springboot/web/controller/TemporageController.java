package com.temporage.book.springboot.web.controller;

import com.temporage.book.springboot.domain.posts.*;
import com.temporage.book.springboot.web.controller.dto.UserInfoDto;
import org.json.simple.JSONObject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@SuppressWarnings("unchecked")
public class TemporageController {

    private final String LOGIN = "login";
    private final String USER = "user";

    @Autowired
    UserInfoRepository userDataRepository;

    @PostMapping("/sign-up")
    public ResponseEntity<HttpStatus> userSignUp(@RequestBody UserInfoDto userInfoDto) {
        if(userDataRepository.findByEmail(userInfoDto.getEmail()) != null)
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 이메일 중복

        UserInfo userData = new UserInfo(userInfoDto.getEmail(),
                BCrypt.hashpw(userInfoDto.getPassword(), BCrypt.gensalt()),
                userInfoDto.getName());

        userDataRepository.save(userData);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/user/sign-in")
    public ResponseEntity<Model> userSignIn(@RequestBody UserInfoDto userInfoDto, HttpServletRequest request, Model model) {
        UserInfo userInfo = userDataRepository.findByEmail(userInfoDto.getEmail());

        if (BCrypt.checkpw(userInfoDto.getPassword(), userInfo.getPassword())) {
            request.getSession().setAttribute(USER, userInfoDto.getEmail());
            //model.addAttribute(USER, userInfoDto.getEmail());
            return new ResponseEntity<>(model, HttpStatus.OK);
        }else{
            //model.addAttribute(USER, userInfoDto.getEmail());
            return new ResponseEntity<>(model, HttpStatus.CONFLICT);
        }
    }

}
