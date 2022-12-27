package com.lcwd.rating.service.controllers;

import com.lcwd.rating.service.entities.Rating;
import com.lcwd.rating.service.payload.ErrorResponse;
import com.lcwd.rating.service.payload.SuccessResponse;
import com.lcwd.rating.service.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<?> createRating(@RequestBody Rating rating){
        try {
            Rating rating1 = this.ratingService.saveRating(rating);
            return SuccessResponse.ResponseHandler("Successfully Created",false, HttpStatus.CREATED,rating1);
        }catch (Exception e){
            return ErrorResponse.ResponseHandler(e.getMessage(),true,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<?> getSingleRating(@PathVariable String ratingId){
        try {
            Rating rating = this.ratingService.getRating(ratingId);
            return SuccessResponse.ResponseHandler("Successfully Fetched",false,HttpStatus.OK,rating);
        }catch (Exception e){
            return ErrorResponse.ResponseHandler(e.getMessage(),true,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllRating(){
        try {
            List<Rating> ratings = this.ratingService.getAllRating();
            return SuccessResponse.ResponseHandler("Successfully Fetched",false,HttpStatus.OK,ratings);
        }catch (Exception e){
            return ErrorResponse.ResponseHandler(e.getMessage(),true,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{ratingId}")
    public ResponseEntity<?> deleteRatingById(@PathVariable String ratingId){
        try {
            this.ratingService.deleteRating(ratingId);
            return SuccessResponse.ResponseHandler("Successfully Deleted",false,HttpStatus.OK,null);
        }catch (Exception e){
            return ErrorResponse.ResponseHandler(e.getMessage(),true,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{ratingId}")
    public ResponseEntity<?> updateRating(@PathVariable String ratingId,@RequestBody Rating rating){
        try {
            Rating rating1 = this.ratingService.updateRating(ratingId,rating);
            return SuccessResponse.ResponseHandler("Successfully Updated",false,HttpStatus.OK,rating1);
        }catch (Exception e){
            return ErrorResponse.ResponseHandler(e.getMessage(),true,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getRatingByUserId(@PathVariable String userId){
        try {
            List<Rating> data = this.ratingService.getRatingByUserId(userId);
            return ResponseEntity.ok(data);
        }catch (Exception e){
            return ErrorResponse.ResponseHandler(e.getMessage(),true,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<?> getRatingByHotelId(@PathVariable String hotelId){
        try {
            List<Rating> data = this.ratingService.getRatingByHotelId(hotelId);
            return SuccessResponse.ResponseHandler("Successfully Fetched",false,HttpStatus.OK,data);
        }catch (Exception e){
            return ErrorResponse.ResponseHandler(e.getMessage(),true,HttpStatus.BAD_REQUEST);
        }
    }

}
