package com.lcwd.rating.service.services;

import com.lcwd.rating.service.entities.Rating;

import java.util.List;

public interface RatingService {

    Rating saveRating(Rating rating);

    List<Rating> getAllRating();

    Rating getRating(String ratingId);

    void deleteRating(String ratingId);

    Rating updateRating(String ratingId,Rating rating);

    List<Rating> getRatingByUserId(String userId);

    List<Rating> getRatingByHotelId(String hotelId);

}
