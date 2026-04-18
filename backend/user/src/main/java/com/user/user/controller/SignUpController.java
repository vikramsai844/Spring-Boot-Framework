package com.user.user.controller;

import com.user.user.model.SignUp;
import com.user.user.service.JwtService;
import com.user.user.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
@CrossOrigin(origins = "http://localhost:5173")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<String> signUp(@RequestBody SignUp signUp){
        Boolean userCreationStatus = signUpService.createNewUser(signUp);
        if (userCreationStatus) {
            return new ResponseEntity<>("User created", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody SignUp login){
        Boolean loginStatus = signUpService.loginUser(login);

        if (loginStatus) {
            String token=jwtService.generateToken(login.getEmail());
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Email or password incorrect", HttpStatus.UNAUTHORIZED);
        }
    }


    @PostMapping("/profile")
    public ResponseEntity<?> dashboard(@RequestHeader(value = "Authorization" , required = false) String token) {

        if (token == null) {
            return new ResponseEntity<>("token not valid", HttpStatus.UNAUTHORIZED);
        }

        String tokens = token.substring(7);

        if (!jwtService.isValid(tokens)) {
            return new ResponseEntity<>("token Invalid ", HttpStatus.UNAUTHORIZED);
        }

        String email = jwtService.extractEmail(tokens);
        return new ResponseEntity<>("Hello" + email + " ", HttpStatus.OK);
    }
}
