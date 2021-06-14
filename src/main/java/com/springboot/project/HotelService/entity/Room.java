package com.springboot.project.HotelService.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="room")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Room {
    @Id
    @Column(name = "room_Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type")
    private RoomTypes roomType;

    @Column(name = "total_rooms")
    private int totalRooms;

    @Column(name = "room_price")
    private int price;

}

