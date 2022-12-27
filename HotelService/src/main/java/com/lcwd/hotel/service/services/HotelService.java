package com.lcwd.hotel.service.services;

import com.lcwd.hotel.service.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel saveHotel(Hotel hotel);

    List<Hotel> getAllHotel();

    Hotel getHotel(String hotelId);

    void deleteHotel(String hotelId);

    Hotel updateHotel(String hotelId,Hotel hotel);

}
