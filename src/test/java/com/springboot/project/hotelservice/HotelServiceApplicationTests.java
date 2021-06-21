package com.springboot.project.hotelservice;

import com.springboot.project.hotelservice.controller.HotelController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class HotelServiceApplicationTests {

    @Autowired
    HotelController hotelController;

    @BeforeEach
    void setup() {
        RestAssuredMockMvc.standaloneSetup(hotelController);
    }

    @Test
    public void testController(){
        Assert.assertNull(hotelController);
    }

}
