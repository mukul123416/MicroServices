package com.lcwd.user.service.controllers;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.payload.ErrorResponse;
import com.lcwd.user.service.payload.SuccessResponse;
import com.lcwd.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        try {
            User user1 = this.userService.saveUser(user);
            return SuccessResponse.ResponseHandler("Successfully Created",false, HttpStatus.CREATED,user1);
        }catch (Exception e){
            return ErrorResponse.ResponseHandler(e.getMessage(),true,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getSingleUser(@PathVariable String userId){
        try {
            User user = this.userService.getUser(userId);
            return SuccessResponse.ResponseHandler("Successfully Fetched",false,HttpStatus.OK,user);
        }catch (Exception e){
            return ErrorResponse.ResponseHandler(e.getMessage(),true,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUser(){
        try {
            List<User> user = this.userService.getAllUser();
            return SuccessResponse.ResponseHandler("Successfully Fetched",false,HttpStatus.OK,user);
        }catch (Exception e){
            return ErrorResponse.ResponseHandler(e.getMessage(),true,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable String userId){
        try {
            this.userService.deleteUser(userId);
            return SuccessResponse.ResponseHandler("Successfully Deleted",false,HttpStatus.OK,null);
        }catch (Exception e){
            return ErrorResponse.ResponseHandler(e.getMessage(),true,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId,@RequestBody User user){
        try {
            User user1 = this.userService.updateUser(userId,user);
            return SuccessResponse.ResponseHandler("Successfully Updated",false,HttpStatus.OK,user1);
        }catch (Exception e){
            return ErrorResponse.ResponseHandler(e.getMessage(),true,HttpStatus.BAD_REQUEST);
        }
    }
}
