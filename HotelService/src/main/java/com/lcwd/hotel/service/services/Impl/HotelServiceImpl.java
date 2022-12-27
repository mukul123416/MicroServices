package com.lcwd.hotel.service.services.Impl;

import com.lcwd.hotel.service.entities.Hotel;
import com.lcwd.hotel.service.exceptions.ResourceNotFoundException;
import com.lcwd.hotel.service.repositories.HotelRepository;
import com.lcwd.hotel.service.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel saveHotel(Hotel hotel) {
        String randomUserId = UUID.randomUUID().toString();
        hotel.setId(randomUserId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotel() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Hotel with given id is not found on server !! : "+hotelId));
    }

    @Override
    public void deleteHotel(String hotelId) {
        hotelRepository.deleteById(hotelId);
    }

    @Override
    public Hotel updateHotel(String hotelId, Hotel hotel) {
        Hotel data=hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Hotel with given id is not found on server !! : "+hotelId));
        data.setName(hotel.getName());
        data.setAbout(hotel.getAbout());
        data.setLocation(hotel.getLocation());
        return hotelRepository.save(data);
    }
}
