package com.springboot.project.HotelService.mapper;

import com.springboot.project.HotelService.entity.Hotel;
import com.springboot.project.HotelService.model.HotelRequest;
import com.springboot.project.HotelService.model.HotelResponse;
import com.springboot.project.HotelService.model.RoomDetails;
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
import java.util.UUID;

@RunWith(SpringRunner.class)
public class HotelMapperTest {

    @InjectMocks
    HotelMapper hotelMapper;

    @Mock
    RoomMapper roomMapper;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConvertEntityToModel(){
        Hotel hotel = Hotel.builder().hotelId(UUID.randomUUID().toString()).hotelName("Mariott").
                city("Hyderabad").build();
        List<Hotel> hotels = Arrays.asList(hotel);
        Mockito.when(roomMapper.convertEntityToModel(Mockito.anyList())).thenReturn(Arrays.asList(RoomDetails.builder().build()));
        List<HotelResponse> hotelResponses = hotelMapper.convertEntityToModel(hotels);
        Assert.assertEquals(hotel.getHotelId(),hotelResponses.get(0).getHotelId());
    }

    @Test
    public void testConvertRequestModelToEntity(){
        Hotel hotel = Hotel.builder().hotelId(UUID.randomUUID().toString())
                .hotelName("Mariott").contact(9898989898L).address("Whitefield")
                .city("Banglore").zipcode(560066L).build();
        HotelRequest hotelRequest = HotelRequest.builder().hotelName(hotel.getHotelName()).city(hotel.getCity())
                .contact(hotel.getContact()).zipcode(hotel.getZipcode()).address(hotel.getAddress()).build();
        Hotel hotelResponse = hotelMapper.convertRequestModelToEntity(hotelRequest, hotel);
        Assert.assertEquals(hotel.getHotelId(),hotelResponse.getHotelId());
    }
}
