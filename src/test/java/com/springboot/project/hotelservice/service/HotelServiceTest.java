package com.springboot.project.hotelservice.service;

import com.springboot.project.hotelservice.entity.Hotel;
import com.springboot.project.hotelservice.exception.IDNotFoundException;
import com.springboot.project.hotelservice.mapper.HotelMapper;
import com.springboot.project.hotelservice.mapper.RoomMapper;
import com.springboot.project.hotelservice.model.HotelRequest;
import com.springboot.project.hotelservice.model.HotelResponse;
import com.springboot.project.hotelservice.repository.HotelRepository;
import com.springboot.project.hotelservice.repository.RoomRepository;
import com.springboot.project.hotelservice.service.impl.HotelServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
public class HotelServiceTest {

    @InjectMocks
    HotelServiceImpl hotelService;

    @Mock
    HotelRepository hotelRepository;

    @Mock
    HotelMapper hotelMapper;

    @Mock
    RoomRepository roomRepository;

    @Mock
    RoomMapper roomMapper;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateHotel(){
        String uuid = UUID.randomUUID().toString();
        Hotel hotel = Hotel.builder().hotelId(uuid).hotelName("HotelName").build();
        Mockito.when(hotelRepository.save(hotel)).thenReturn(hotel);
        String hotelId = hotelService.createHotel(hotel);
        Assert.assertNotNull(hotelId);
    }

    @Test
    public void testGetHotels(){
        String uuid = UUID.randomUUID().toString();
        Hotel hotel = Hotel.builder().hotelId(uuid).hotelName("HotelName").build();
        List<Hotel> hotels = Arrays.asList(hotel);
        List<HotelResponse> hotelResponses = new ArrayList<>();
        Mockito.when(hotelRepository.findAll()).thenReturn(hotels);
        Mockito.when(hotelMapper.convertEntityToModel(Mockito.anyList())).thenReturn(hotelResponses);
        Assert.assertNotNull(hotelService.getHotels());
    }

    @Test
    public void testGetHotel(){
        String uuid = UUID.randomUUID().toString();
        Hotel hotel = Hotel.builder().hotelId(uuid).hotelName("Mariott").build();
        HotelResponse hotelResponse = HotelResponse.builder().hotelId(hotel.getHotelId())
                .hotelName("Mariott").contact(9898989898L).address("Whitefield")
                .city("Banglore").zipcode(560066L).build();
        Mockito.when(hotelRepository.findById(hotel.getHotelId())).thenReturn(Optional.of(hotel));
        Mockito.when(hotelMapper.convertEntityToModel(hotel)).thenReturn(hotelResponse);
        hotelResponse = hotelService.getHotel(hotel.getHotelId());
        Assert.assertEquals(hotel.getHotelId(),hotelResponse.getHotelId());
        Assert.assertEquals(hotel.getHotelName(),hotelResponse.getHotelName());
    }

    @Test(expected = IDNotFoundException.class)
    public void testGetHotelException(){
        String uuid = UUID.randomUUID().toString();
        Hotel hotel = Hotel.builder().hotelId(uuid).hotelName("HotelName").build();
        HotelResponse hotelResponse = HotelResponse.builder().hotelId(hotel.getHotelId())
                .hotelName("HotelName").build();
        Mockito.when(hotelRepository.findById(Mockito.anyString())).thenThrow(IDNotFoundException.class);
        Mockito.when(hotelMapper.convertEntityToModel(Mockito.any(Hotel.class))).thenThrow(new IDNotFoundException());
        hotelResponse = hotelService.getHotel(hotel.getHotelId());
        Assert.assertEquals(hotel.getHotelId(),hotelResponse.getHotelId());
        Assert.assertEquals(hotel.getHotelName(),hotelResponse.getHotelName());
    }

    @Test
    public void testUpdateHotel(){
        String uuid = UUID.randomUUID().toString();
        Hotel hotel = Hotel.builder().hotelId(uuid).hotelName("HotelName").build();
        HotelRequest hotelRequest = HotelRequest.builder().hotelName("Updated HotelName").build();
        HotelResponse hotelResponse = HotelResponse.builder().hotelId(uuid).hotelName("HotelName").build();
        Mockito.when(hotelRepository.findById(hotel.getHotelId())).thenReturn(Optional.of(hotel));
        Mockito.when(hotelMapper.convertRequestModelToEntity(hotelRequest,hotel)).thenReturn(hotel);
        Mockito.when(hotelRepository.save(Mockito.any())).thenReturn(hotel);
        Mockito.when(hotelMapper.convertEntityToModel(hotel)).thenReturn(hotelResponse);
        hotelResponse = hotelService.updateHotel(hotelRequest, hotel.getHotelId());
        Assert.assertEquals(hotel.getHotelId(),hotelResponse.getHotelId());
        Assert.assertEquals(hotel.getHotelName(),hotelResponse.getHotelName());
    }

    @Test(expected = IDNotFoundException.class)
    public void testUpdateHotelException(){
        String uuid = UUID.randomUUID().toString();
        Hotel hotel = Hotel.builder().hotelId(uuid).hotelName("HotelName").build();
        HotelRequest hotelRequest = HotelRequest.builder().hotelName("Updated HotelName").build();
        HotelResponse hotelResponse = HotelResponse.builder().hotelId(uuid).hotelName("HotelName").build();
        Mockito.when(hotelRepository.findById(hotel.getHotelId())).thenThrow(IDNotFoundException.class);
        Mockito.when(hotelMapper.convertRequestModelToEntity(hotelRequest,hotel)).thenReturn(hotel);
        Mockito.when(hotelRepository.save(Mockito.any())).thenReturn(hotel);
        Mockito.when(hotelMapper.convertEntityToModel(hotel)).thenReturn(hotelResponse);
        hotelResponse = hotelService.updateHotel(hotelRequest, hotel.getHotelId());
        Assert.assertEquals(hotel.getHotelId(),hotelResponse.getHotelId());
        Assert.assertEquals(hotel.getHotelName(),hotelResponse.getHotelName());
    }

    @Test
    public void testDeleteHotel(){
        hotelService.deleteHotel(UUID.randomUUID().toString());
    }
}
