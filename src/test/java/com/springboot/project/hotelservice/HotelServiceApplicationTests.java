package com.springboot.project.hotelservice;

import com.springboot.project.hotelservice.entity.Hotel;
import com.springboot.project.hotelservice.entity.Room;
import com.springboot.project.hotelservice.entity.RoomTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HotelServiceApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateHotel() {
        Room room = Room.builder().id(1).roomType(RoomTypes.KING_BED).build();
        Hotel hotel = Hotel.builder().hotelId("12c9f1e4a1a04878860b588f42d37a6d")
                .hotelName("Name").roomDetails(Arrays.asList(room)).build();
        HttpEntity<Hotel> entity = new HttpEntity<>(hotel);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange("/hotels", HttpMethod.POST, entity,
                String.class);
        Assertions.assertEquals(responseEntity.getStatusCodeValue(),HttpStatus.CREATED.value());
    }

    @Test
    void testCreateHotelBadRequest() {
        Hotel hotel = Hotel.builder().build();
        HttpEntity<Hotel> entity = new HttpEntity<>(hotel);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange("/hotels", HttpMethod.POST, entity,
                String.class);
        Assertions.assertEquals(responseEntity.getStatusCodeValue(),HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void testDeleteHotel() {
        testRestTemplate.execute("/hotels/12c9f1e4a1a04878860b588f42d37a6d",
                HttpMethod.DELETE,null, null);
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/hotels/12c9f1e4a1a04878860b588f42d37a6d", String.class);
        Assertions.assertEquals(responseEntity.getStatusCodeValue(),HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
