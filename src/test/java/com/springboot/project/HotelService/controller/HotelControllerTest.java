package com.springboot.project.HotelService.controller;

import com.springboot.project.HotelService.entity.Hotel;
import com.springboot.project.HotelService.model.HotelRequest;
import com.springboot.project.HotelService.model.HotelResponse;
import com.springboot.project.HotelService.service.impl.HotelServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class HotelControllerTest {

    @InjectMocks
    HotelControllerImpl hotelController;

    @Mock
    HotelServiceImpl hotelService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateHotel() {
        Hotel hotel = Hotel.builder().hotelId(UUID.randomUUID().toString()).build();
        Mockito.when(hotelService.createHotel(any(Hotel.class))).thenReturn(hotel.getHotelId());
        ResponseEntity<String> hotelResponse = hotelController.createHotel(hotel);
        assertEquals(HttpStatus.CREATED, hotelResponse.getStatusCode());
    }

    @Test
    public void testGetHotels(){
        HotelResponse hotelResponse = HotelResponse.builder().hotelId(UUID.randomUUID().toString()).build();
        List<HotelResponse> hotelResponses = Arrays.asList(hotelResponse);
        Mockito.when(hotelService.getHotels()).thenReturn(hotelResponses);
        ResponseEntity<List<HotelResponse>> response = hotelController.getHotels();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetHotel(){
        Hotel hotel = Hotel.builder().hotelId(UUID.randomUUID().toString()).build();
        HotelResponse hotelResponse = HotelResponse.builder().hotelId(UUID.randomUUID().toString()).build();
        Mockito.when(hotelService.getHotel(hotel.getHotelId())).thenReturn(hotelResponse);
        ResponseEntity<HotelResponse> response = hotelController.getHotel(hotel.getHotelId());
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testUpdateHotel(){
        HotelRequest hotelRequest = HotelRequest.builder().hotelName("Orbis").build();
        HotelResponse hotelResponse = HotelResponse.builder().hotelId(UUID.randomUUID().toString()).build();
        Mockito.when(hotelService.updateHotel(hotelRequest,UUID.randomUUID().toString())).thenReturn(hotelResponse);
        ResponseEntity<HotelResponse> response = hotelController.updateHotel(hotelRequest, UUID.randomUUID().toString());
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testDeleteHotel(){
        Hotel hotel = Hotel.builder().hotelId(UUID.randomUUID().toString()).build();
        Mockito.when(hotelService.deleteHotel(hotel.getHotelId())).thenReturn(hotel.getHotelId());
        ResponseEntity<String> response = hotelController.deleteHotel(hotel.getHotelId());
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

}
