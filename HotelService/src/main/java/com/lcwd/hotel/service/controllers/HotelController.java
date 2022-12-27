package com.lcwd.hotel.service.controllers;

import com.lcwd.hotel.service.entities.Hotel;
import com.lcwd.hotel.service.payload.ErrorResponse;
import com.lcwd.hotel.service.payload.SuccessResponse;
import com.lcwd.hotel.service.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<?> createHotel(@RequestBody Hotel hotel){
        try {
            Hotel hotel1 = this.hotelService.saveHotel(hotel);
            return SuccessResponse.ResponseHandler("Successfully Created",false, HttpStatus.CREATED,hotel1);
        }catch (Exception e){
            return ErrorResponse.ResponseHandler(e.getMessage(),true,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<?> getSingleHotel(@PathVariable String hotelId){
        try {
            Hotel hotel = this.hotelService.getHotel(hotelId);
            return ResponseEntity.ok(hotel);
        }catch (Exception e){
            return ErrorResponse.ResponseHandler(e.getMessage(),true,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllHotel(){
        try {
            List<Hotel> hotels = this.hotelService.getAllHotel();
            return SuccessResponse.ResponseHandler("Successfully Fetched",false,HttpStatus.OK,hotels);
        }catch (Exception e){
            return ErrorResponse.ResponseHandler(e.getMessage(),true,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<?> deleteHotelById(@PathVariable String hotelId){
        try {
            this.hotelService.deleteHotel(hotelId);
            return SuccessResponse.ResponseHandler("Successfully Deleted",false,HttpStatus.OK,null);
        }catch (Exception e){
            return ErrorResponse.ResponseHandler(e.getMessage(),true,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<?> updateUser(@PathVariable String hotelId,@RequestBody Hotel hotel){
        try {
            Hotel hotel1 = this.hotelService.updateHotel(hotelId,hotel);
            return SuccessResponse.ResponseHandler("Successfully Updated",false,HttpStatus.OK,hotel1);
        }catch (Exception e){
            return ErrorResponse.ResponseHandler(e.getMessage(),true,HttpStatus.BAD_REQUEST);
        }
    }
}
