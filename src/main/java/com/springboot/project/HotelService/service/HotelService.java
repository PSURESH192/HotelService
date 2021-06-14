package com.springboot.project.HotelService.service;


import com.springboot.project.HotelService.entity.Hotel;
import com.springboot.project.HotelService.model.HotelRequest;
import com.springboot.project.HotelService.model.HotelResponse;

import java.util.List;

public interface HotelService {

    String createHotel(Hotel hotel);

    List<HotelResponse> getHotels();

    HotelResponse getHotel(String id);

    HotelResponse updateHotel(HotelRequest hotelRequest , String id);

    String deleteHotel(String id);

}
