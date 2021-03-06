package com.springboot.project.hotelservice.controller;

import com.springboot.project.hotelservice.entity.Hotel;
import com.springboot.project.hotelservice.model.HotelRequest;
import com.springboot.project.hotelservice.model.HotelResponse;
import com.springboot.project.hotelservice.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
public class HotelControllerImpl implements HotelController{

    @Autowired
    private HotelService hotelService;

    @Override
    @PostMapping("/hotels")
    public ResponseEntity<String> createHotel(@Valid @RequestBody Hotel hotel) {
        return new ResponseEntity<>(hotelService.createHotel(hotel), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/hotels")
    public ResponseEntity<List<HotelResponse>> getHotels() {
        return new ResponseEntity<>(hotelService.getHotels(),HttpStatus.OK);
    }

    @Override
    @GetMapping("/hotels/{id}")
    public ResponseEntity<HotelResponse> getHotel(@NotNull @NotBlank @PathVariable("id") String id) {
        return new ResponseEntity<>(hotelService.getHotel(id),HttpStatus.OK);
    }

    @Override
    @PutMapping("/hotels/{id}")
    public ResponseEntity<HotelResponse> updateHotel(@Valid @NotNull @RequestBody HotelRequest hotelRequest,
                                             @NotNull @NotBlank @PathVariable("id") String id) {
        return new ResponseEntity<>(hotelService.updateHotel(hotelRequest, id),HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/hotels/{id}")
    public ResponseEntity<String> deleteHotel(@NotNull @NotBlank @PathVariable("id") String id) {
        return new ResponseEntity<>(hotelService.deleteHotel(id),HttpStatus.OK);
    }


}