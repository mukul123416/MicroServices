package com.lcwd.user.service.services.Impl;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found on server !! : "+userId));
        // fetch rating of the above user from RATING SERVICE
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+userId, Rating[].class);
        List<Rating> ratings= Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList=ratings.stream().map(rating -> {
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//            Hotel hotel = forEntity.getBody();
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(String userId, User user) {
        User data=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found on server !! : "+userId));
        data.setName(user.getName());
        data.setEmail(user.getEmail());
        data.setAbout(user.getAbout());
        return userRepository.save(data);
    }
}
