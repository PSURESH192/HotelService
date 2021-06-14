package com.springboot.project.hotelservice.service.impl;

import com.springboot.project.hotelservice.entity.Hotel;
import com.springboot.project.hotelservice.exception.IDNotFoundException;
import com.springboot.project.hotelservice.mapper.HotelMapper;
import com.springboot.project.hotelservice.model.HotelRequest;
import com.springboot.project.hotelservice.model.HotelResponse;
import com.springboot.project.hotelservice.repository.HotelRepository;
import com.springboot.project.hotelservice.repository.RoomRepository;
import com.springboot.project.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String createHotel(Hotel hotel)
    {
        hotel.setHotelId(UUID.randomUUID().toString().replace("-", ""));
        Hotel saveHotel = hotelRepository.save(hotel);
        return saveHotel.getHotelId();
    }

    @Override
    public List<HotelResponse> getHotels() {
        return hotelMapper.convertEntityToModel(hotelRepository.findAll());
    }

    @Override
    public HotelResponse getHotel(String id){
        try{
            Optional<Hotel> hotelOptional = hotelRepository.findById(id);
            return hotelMapper.convertEntityToModel(hotelOptional.isPresent() ? hotelOptional.get() : null);
        } catch (Exception ex){
            throw new IDNotFoundException();
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public HotelResponse updateHotel(HotelRequest hotelRequest, String id) {
        try {
            Optional<Hotel> hotelOptional = hotelRepository.findById(id);
            Hotel hotel = hotelRepository.save(hotelMapper.convertRequestModelToEntity(hotelRequest,
                    hotelOptional.isPresent() ? hotelOptional.get() : null));
            return hotelMapper.convertEntityToModel(hotel);
        } catch (Exception ex){
            throw new IDNotFoundException();
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String deleteHotel(String id) {
        hotelRepository.deleteById(id);
        return id;
    }
}
