package com.user.user.service;

import com.user.user.model.User;
import com.user.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;



    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createNewUser(User user) {

       return  userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        User existingUser=userRepository.findById(id).orElse(null);
        if(existingUser!=null){
            existingUser.setName(user.getName());
           return userRepository.save(existingUser);
        }
        return null;
    }


    public String deleteUser(Long id) {
        User delete=userRepository.findById(id).orElse(null);
        if(delete!=null) {
            userRepository.deleteById(id);
            return "deleted";
        }
        else{
            return "user not found";

    }
}


    public List<User> getAllUsers() {
       List<User> users=userRepository.findAll();
       return users;
    }
}
