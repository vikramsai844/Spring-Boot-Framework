package com.user.user.service;

import com.user.user.model.SignUp;
import com.user.user.repository.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Autowired
   private SignUpRepository signUpRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Boolean createNewUser(SignUp signUp) {
        SignUp  existingData=  signUpRepository.findByEmail(signUp.getEmail());
        if(existingData==null){
           signUp.setPassword(passwordEncoder.encode(signUp.getPassword()));
            signUpRepository.save(signUp);
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean loginUser(SignUp login) {
      SignUp  existingUser =  signUpRepository.findByEmail(login.getEmail());

      if(existingUser!=null){
          if(passwordEncoder.matches(login.getPassword(), existingUser.getPassword())){
              return true;
          }
          else {
              return false;
          }
      }
      return false;

    }
}

