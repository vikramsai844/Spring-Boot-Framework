package com.user.user.controller;

import com.user.user.model.User;
import com.user.user.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        User user=userService.getUser(id);
        if(user!=null){
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(user,HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
       User newUser= userService.createNewUser(user);

       if (newUser!=null){
        return    new ResponseEntity<>(newUser,HttpStatus.OK);
       }
       else {
         return new ResponseEntity<>(newUser,HttpStatus.BAD_REQUEST);
       }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User user){

        User updatedUser=userService.updateUser(id,user);

        if(updatedUser!=null){
            System.out.println("user updated"+updatedUser);
            return new ResponseEntity<>(updatedUser,HttpStatus.OK);
        }
        else {
            System.out.println("user not updated"+updatedUser);
            return new ResponseEntity<>(updatedUser,HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
       String deleteStatus= userService.deleteUser(id);
       if(deleteStatus.equals("deleted")){
           return  new ResponseEntity<>("deleted",HttpStatus.OK);
       }
       else {
           return new ResponseEntity<>("user not found",HttpStatus.NOT_FOUND);
       }
    }


    @GetMapping
    public  ResponseEntity<List<User>> getAllUsers(){
      List<User> users=  userService.getAllUsers();
      if(users!=null){
          return new ResponseEntity<>(users,HttpStatus.OK);
      }
      return null;
    }


}
