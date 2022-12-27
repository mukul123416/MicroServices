package com.lcwd.hotel.service.payload;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class ErrorResponse {

    public static ResponseEntity<Object> ResponseHandler(String message, Boolean error, HttpStatus status){
        HashMap<String,Object> map = new HashMap<>();
        map.put("message",message);
        map.put("error",error);
        map.put("status",status.value());
        return new ResponseEntity<Object>(map,status);
    }

}
