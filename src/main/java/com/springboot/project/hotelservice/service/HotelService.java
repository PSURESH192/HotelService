package com.springboot.project.hotelservice.service;


import com.springboot.project.hotelservice.entity.Hotel;
import com.springboot.project.hotelservice.model.HotelRequest;
import com.springboot.project.hotelservice.model.HotelResponse;

import java.util.List;

public interface HotelService {

    String createHotel(Hotel hotel);

    List<HotelResponse> getHotels();

    HotelResponse getHotel(String id);

    HotelResponse updateHotel(HotelRequest hotelRequest , String id);

    String deleteHotel(String id);

}
