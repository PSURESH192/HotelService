package com.springboot.project.hotelservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.project.hotelservice.entity.Hotel;
import com.springboot.project.hotelservice.model.HotelRequest;
import com.springboot.project.hotelservice.service.HotelService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class HotelControllerTest {

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    HotelService hotelService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    public void testCreateHotel() throws Exception {
        Hotel hotel = Hotel.builder().hotelName("HotelName").contact(9898989898L).address("Whitefield")
                .city("Banglore").zipcode(560066L).build();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/hotels")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(hotel))).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(201, status);
    }

    @Test
    public void testGetHotels() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/hotels"))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void testGetHotel() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/hotels/12c9f1e4a1a04878860b588f42d37a6d"))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void testUpdateHotel() throws Exception {
        HotelRequest request = HotelRequest.builder().hotelName("HotelNameUpdated").build();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/hotels/12c9f1e4a1a04878860b588f42d37a6d")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(request))).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void testDeleteHotel() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/hotels/12c9f1e4a1a04878860b588f42d37a6d"))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

}
