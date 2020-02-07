package com.temporage.book.springboot.web.controller;

import com.temporage.book.springboot.domain.posts.*;
import org.json.simple.JSONObject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RestController
@SuppressWarnings("unchecked")
public class TemporageController {

    @Autowired
    TemporageDataRepository appDataRepository;
    @Autowired
    TemporageUserDataRepository userDataRepository;
    @Autowired
    TemporageSessionRepository sessionRepository;

    @PostMapping("/save")
    public String dataSave(@RequestParam("key") String key, @RequestParam("value") String value){
        TemporageData data = new TemporageData();

        try{
            if(key != null && value != null){
                data.setKey(key);
                data.setValue(value);
                appDataRepository.save(data);

                return "success";
            }else{
                return "fail : key or value is empty";
            }
        }catch (Exception e){
            return e.toString();
        }
    }

    @GetMapping("/getData")
    public List<TemporageData> dataResponse(@RequestParam("key") String key){
        LocalDateTime current_date = LocalDateTime.now(); //현재 시각 받아옴
        return appDataRepository.findByKey(key, current_date);
    }

    @PostMapping("/sign-up")
    public String user_sign_up(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("name") String name){
        TemporageUserData user_data = new TemporageUserData();

        try{
            if(email != null && password != null && name != null){
                //이메일이 중첩되지 않은 경우
                if(userDataRepository.findByEmail(email) == null){
                    user_data.setEmail(email);
                    user_data.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
                    user_data.setName(name);

                    userDataRepository.save(user_data);
                    return "success";
                }else{
                    return "이미 가입된 이메일 입니다.";
                }


            }else{
                return "fail : some value has NULL";
            }
        }catch (Exception e){
            return e.toString();
        }
    }


    @PostMapping("/sign-in")
    public JSONObject user_sign_in(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest request){
        TemporageUserData user_data = userDataRepository.findByEmail(email);
        JSONObject result = new JSONObject(new HashMap<String, String>());

        if(user_data == null){
            result.put("result", 2);
            result.put("message", "email is not corret");
            return result;
        }

        if(BCrypt.checkpw(password, user_data.getPassword())){
            HttpSession session = request.getSession();

            TemporageSession session_data = new TemporageSession(session.getId(), email); //SESSION Part. 02/07
            sessionRepository.save(session_data);

            result.put("result", 1);
            result.put("session_id", session.getId());

            return result;
        }else{
            result.put("result", 3);
            result.put("message", "not corret password or email");
            return result;
        }
    }

    //SESSION Part. 02/07
    @PostMapping("/session-check")
    public JSONObject user_session_check(@RequestParam("email") String email, @CookieValue("JSESSIONID") String c_sessionId){
        JSONObject result = new JSONObject();
        TemporageSession sessionInfo = sessionRepository.findByEmail(email);

        if(c_sessionId.equals(sessionInfo.getSession_id())){
            result.put("result", 1);
            result.put("message", "Session ID is correct");
            return result;
        }else{
            result.put("result", 4);
            result.put("message", "SESSION ID is not correct.");
            result.put("Client SessionID", c_sessionId);
            result.put("Server SessionID", sessionInfo.getSession_id());
            return result;
        }
    }

}
