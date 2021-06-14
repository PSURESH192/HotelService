package com.springboot.project.hotelservice.mapper;

import com.springboot.project.hotelservice.entity.Room;
import com.springboot.project.hotelservice.entity.RoomTypes;
import com.springboot.project.hotelservice.model.RoomDetails;
import com.springboot.project.hotelservice.repository.RoomRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class RoomMapperTest {

    @InjectMocks
    RoomMapper roomMapper;

    @Mock
    RoomRepository roomRepository;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConvertEntityToModel(){
        Room room = Room.builder().roomType(RoomTypes.KING_BED).totalRooms(10).price(1500).build();
        List<Room> rooms = Arrays.asList(room);
        List<RoomDetails> roomDetails = roomMapper.convertEntityToModel(rooms);
        Assert.assertEquals(roomDetails.get(0).getRoomId(),room.getId());
    }

    @Test
    public void testConvertModelToEntity(){
        RoomDetails roomDetail = RoomDetails.builder().roomId(1).roomType(RoomTypes.KING_BED.toString()).totalRooms(10).price(1500).build();
        List<RoomDetails> roomDetails = Arrays.asList(roomDetail);
        Room room = Room.builder().roomType(RoomTypes.KING_BED).totalRooms(roomDetail.getTotalRooms()).price(1500).build();
        Mockito.when(roomRepository.findById(Mockito.any())).thenReturn(Optional.of(room));
        List<Room> rooms = roomMapper.convertModelToEntity(roomDetails);
        Assert.assertNotNull(rooms);
    }

}
