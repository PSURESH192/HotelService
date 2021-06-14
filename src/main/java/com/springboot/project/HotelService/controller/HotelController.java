package com.springboot.project.HotelService.controller;

import com.springboot.project.HotelService.entity.Hotel;
import com.springboot.project.HotelService.model.HotelRequest;
import com.springboot.project.HotelService.model.HotelResponse;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

public interface HotelController {

    @ApiOperation(value = "Create Hotel", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> createHotel(@Valid @RequestBody Hotel hotel);

    @ApiOperation(value = "Get All Hotels", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<HotelResponse>> getHotels();

    @ApiOperation(value = "Get Hotel By ID", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<HotelResponse> getHotel(@NotNull @NotBlank @PathVariable("id") String id);

    @ApiOperation(value = "Update Hotel By ID", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<HotelResponse> updateHotel(@RequestBody HotelRequest hotelRequest, @NotNull @NotBlank @PathVariable("id") String id);

    @ApiOperation(value = "Delete Hotel By ID", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> deleteHotel(@NotNull @NotBlank @PathVariable("id") String id);
}
