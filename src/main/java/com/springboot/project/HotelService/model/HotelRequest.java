package com.springboot.project.HotelService.model;

import lombok.*;

import javax.persistence.Column;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class HotelRequest {

    private String hotelName;
    private Long contact;
    private String address;
    private String city;
    private Long zipcode;

    private List<RoomDetails> roomDetails;
}
