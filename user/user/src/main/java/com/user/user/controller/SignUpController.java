package com.user.user.controller;

import com.user.user.model.SignUp;
import com.user.user.service.SignUpService;
import com.user.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
@CrossOrigin(origins = "http://localhost:5173")
public class SignUpController {

    @Autowired
    private  SignUpService signUpService;

    @PostMapping("/register")
    public ResponseEntity<String> signUp(@RequestBody SignUp signUp){
    Boolean userCreationStatus= signUpService.createNewUser(signUp);
        if(userCreationStatus){
            return new ResponseEntity<>("user Created", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("user already Exist",HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody SignUp login){
       Boolean loginStatus= signUpService.loginUser(login);
       if(loginStatus){
           return new ResponseEntity<>("user successfully login",HttpStatus.OK);
       }
       else {
           return new ResponseEntity<>("email or password is incorrect",HttpStatus.UNAUTHORIZED);
       }
    }

}
