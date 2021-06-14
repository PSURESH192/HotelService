package com.springboot.project.HotelService.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RoomDetails {

    private int roomId;
    private String roomType;
    private int totalRooms;
    private int price;

}
