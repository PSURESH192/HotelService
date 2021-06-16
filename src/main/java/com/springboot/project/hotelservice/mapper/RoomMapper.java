package com.springboot.project.hotelservice.mapper;

import com.springboot.project.hotelservice.entity.Room;
import com.springboot.project.hotelservice.entity.RoomTypes;
import com.springboot.project.hotelservice.model.RoomDetails;
import com.springboot.project.hotelservice.repository.RoomRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RoomMapper {

    @Autowired
    private RoomRepository roomRepository;

    public List<RoomDetails> convertEntityToModel(List<Room> rooms) {
        List<RoomDetails> roomDetails = new ArrayList<>();
        if(Optional.ofNullable(rooms).isPresent() && !rooms.isEmpty())
        {
            roomDetails = rooms.stream().filter(Objects::nonNull)
                    .map(this::convertEntityToModel)
                    .collect(Collectors.toList());
        }
        return roomDetails;
    }


    public RoomDetails convertEntityToModel(Room room) {
        return RoomDetails.builder().roomId(room.getId()).roomType(room.getRoomType().toString())
                .totalRooms(room.getTotalRooms()).price(room.getPrice())
                .build();
    }

    public List<Room> convertModelToEntity(List<RoomDetails> roomDetails) {
        List<Room> rooms = new ArrayList<>();
        if(Optional.ofNullable(roomDetails).isPresent() && !roomDetails.isEmpty())
        {
            rooms = roomDetails.stream().filter(Objects::nonNull)
                    .map(this::convertModelToEntity)
                    .collect(Collectors.toList());
        }
        return rooms;
    }

    private Room convertModelToEntity(RoomDetails roomDetails) {
        Optional<Room> optionalRoom = roomRepository.findById(roomDetails.getRoomId());

        Room room = optionalRoom.isPresent() ? optionalRoom.get() : new Room();

        if (StringUtils.isNotBlank(roomDetails.getRoomType())) {
            room.setRoomType(RoomTypes.valueOf(roomDetails.getRoomType()));
        }

        if (Optional.ofNullable(roomDetails.getTotalRooms()).isPresent()) {
            room.setTotalRooms(roomDetails.getTotalRooms());
        }

        if (Optional.ofNullable(roomDetails.getPrice()).isPresent()) {
            room.setPrice(roomDetails.getPrice());
        }
        return roomRepository.save(room);
    }

}
