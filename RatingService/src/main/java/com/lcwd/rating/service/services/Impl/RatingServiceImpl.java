package com.lcwd.rating.service.services.Impl;

import com.lcwd.rating.service.entities.Rating;
import com.lcwd.rating.service.exceptions.ResourceNotFoundException;
import com.lcwd.rating.service.repositories.RatingRepository;
import com.lcwd.rating.service.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating saveRating(Rating rating) {
        String randomUserId = UUID.randomUUID().toString();
        rating.setRatingId(randomUserId);
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRating() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating getRating(String ratingId) {
        return ratingRepository.findById(ratingId).orElseThrow(()->new ResourceNotFoundException("Rating with given id is not found on server !! : "+ratingId));
    }

    @Override
    public void deleteRating(String ratingId) {
        ratingRepository.deleteById(ratingId);
    }

    @Override
    public Rating updateRating(String ratingId, Rating rating) {
        Rating data=ratingRepository.findById(ratingId).orElseThrow(()->new ResourceNotFoundException("Rating with given id is not found on server !! : "+ratingId));
        data.setRating(rating.getRating());
        data.setFeedback(rating.getFeedback());
        return ratingRepository.save(data);
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
