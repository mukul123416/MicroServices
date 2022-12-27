package com.lcwd.hotel.service.payload;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class SuccessResponse {

    public static ResponseEntity<Object> ResponseHandler(String message, Boolean error, HttpStatus status, Object data){
        HashMap<String,Object> map = new HashMap<>();
        map.put("message",message);
        map.put("error",error);
        map.put("status",status.value());
        map.put("data",data);
        return new ResponseEntity<Object>(map,status);
    }

}
