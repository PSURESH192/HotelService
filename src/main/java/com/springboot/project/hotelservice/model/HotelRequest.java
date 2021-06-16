package com.springboot.project.hotelservice.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HotelRequest {

    private String hotelName;
    private Long contact;
    private String address;
    private String city;
    private Long zipcode;

    private List<RoomDetails> roomDetails;
}
