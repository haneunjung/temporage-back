package com.temporage.book.springboot.controller;

import com.temporage.book.springboot.dto.UserInfoDto;
import com.temporage.book.springboot.domain.UserInfo;
import com.temporage.book.springboot.repository.UserInfoRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@SuppressWarnings("unchecked")
public class UserController {

    private final String USER = "user";

    @Autowired
    UserInfoRepository userDataRepository;

    @PostMapping("/sign-up")
    public ResponseEntity<HttpStatus> userSignUp(@RequestBody UserInfoDto userInfoDto) {
        if(userDataRepository.findByUserEmail(userInfoDto.getUserEmail()) != null)
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 이메일 중복

        UserInfo userData = new UserInfo(userInfoDto.getUserEmail(),
                BCrypt.hashpw(userInfoDto.getUserPw(), BCrypt.gensalt()),
                userInfoDto.getUserName());

        userDataRepository.save(userData);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/sign-in")
    public ResponseEntity<HttpStatus> userSignIn(@RequestBody UserInfoDto userInfoDto, HttpServletRequest request) {
        UserInfo userInfo = userDataRepository.findByUserEmail(userInfoDto.getUserEmail());

        if (BCrypt.checkpw(userInfoDto.getUserPw(), userInfo.getUserPw())) {
            request.getSession().setAttribute(USER, userInfo.getUserIdx());
            //model.addAttribute(USER, userInfoDto.getEmail());
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            //model.addAttribute(USER, userInfoDto.getEmail());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/user/id/{userEmail}")
    public ResponseEntity<HttpStatus> isEmailExist(@PathVariable("userEmail") String userEmail) {
        UserInfo userInfo = userDataRepository.findByUserEmail(userEmail);
        try {
            if (userInfo.getUserEmail() == null) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

}
