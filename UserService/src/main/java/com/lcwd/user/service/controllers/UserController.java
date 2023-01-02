package com.lcwd.user.service.controllers;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.payload.ErrorResponse;
import com.lcwd.user.service.payload.SuccessResponse;
import com.lcwd.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
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

    int retryCount=1;

    @GetMapping("/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    @Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<?> getSingleUser(@PathVariable String userId){
        System.out.println("Get Single User Handler: UserController");
        System.out.println("Retry count: {} "+retryCount);
        retryCount++;
        User user = this.userService.getUser(userId);
        return SuccessResponse.ResponseHandler("Successfully Fetched",false,HttpStatus.OK,user);
    }

    //creating fall back method for circuit breaker
    public ResponseEntity<?> ratingHotelFallback(String userId,Exception ex){
        System.out.println("Fallback is executed because service is down : "+ex.getMessage());
        User user = User.builder().email("dummy@gmail.com").name("Dummy").about("This user is created dummy because some service is down").userId("12345").build();
        return new ResponseEntity<>(user,HttpStatus.OK);
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
