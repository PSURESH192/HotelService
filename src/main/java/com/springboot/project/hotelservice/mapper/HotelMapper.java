package com.springboot.project.hotelservice.mapper;

import com.springboot.project.hotelservice.entity.Hotel;
import com.springboot.project.hotelservice.model.HotelRequest;
import com.springboot.project.hotelservice.model.HotelResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class HotelMapper {

    @Autowired
    private RoomMapper roomMapper;

    public List<HotelResponse> convertEntityToModel(List<Hotel> hotels) {
        List<HotelResponse> hotelResponses = new ArrayList<>();
        if(Optional.ofNullable(hotels).isPresent() && !hotels.isEmpty())
        {
            hotelResponses = hotels.stream().filter(Objects::nonNull)
                    .map(this::convertEntityToModel)
                    .collect(Collectors.toList());
        }
        return hotelResponses;
    }

    public HotelResponse convertEntityToModel(Hotel hotelEntity) {

        return HotelResponse.builder()
        .hotelId(hotelEntity.getHotelId())
        .hotelName(hotelEntity.getHotelName())
        .contact(hotelEntity.getContact())
        .address(hotelEntity.getAddress())
        .city(hotelEntity.getCity())
        .zipcode(hotelEntity.getZipcode())
        .roomDetails(roomMapper.convertEntityToModel(hotelEntity.getRoomDetails())).build();
    }

    public Hotel convertRequestModelToEntity(HotelRequest hotelRequest, Hotel hotel) {

        if (Optional.ofNullable(hotelRequest).isPresent()) {

            if (StringUtils.isNotBlank(hotelRequest.getHotelName())) {
                hotel.setHotelName(hotelRequest.getHotelName());
            }

            if (Optional.ofNullable(hotelRequest.getContact()).isPresent()) {
                hotel.setContact(hotelRequest.getContact());
            }

            if (StringUtils.isNotBlank(hotelRequest.getAddress())) {
                hotel.setAddress(hotelRequest.getAddress());
            }

            if (StringUtils.isNotBlank(hotelRequest.getCity())) {
                hotel.setCity(hotelRequest.getCity());
            }

            if (Optional.ofNullable(hotelRequest.getZipcode()).isPresent()) {
                hotel.setZipcode(hotelRequest.getZipcode());
            }

            hotel.setRoomDetails(roomMapper.convertModelToEntity(hotelRequest.getRoomDetails()));
        }
        return hotel;
    }
}
