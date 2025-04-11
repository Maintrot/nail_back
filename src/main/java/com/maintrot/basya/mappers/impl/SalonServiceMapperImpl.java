package com.maintrot.basya.mappers.impl;

import com.maintrot.basya.dtoes.SalonServiceRequest;
import com.maintrot.basya.dtoes.SalonServiceResponse;
import com.maintrot.basya.mappers.SalonServiceMapper;
import com.maintrot.basya.models.SalonService;
import org.springframework.stereotype.Component;

@Component
public class SalonServiceMapperImpl implements SalonServiceMapper {

    @Override
    public SalonServiceResponse toResponce(SalonService salonService) {
        SalonServiceResponse response = new SalonServiceResponse();
        response.setId(salonService.getId());
        response.setName(salonService.getName());
        response.setCost(salonService.getCost());
        response.setDescription(salonService.getDescription());
        return response;
    }

    @Override
    public SalonService toEntity(SalonServiceRequest salonServiceRequest) {
        SalonService salonService = new SalonService();
        salonService.setName(salonServiceRequest.getName());
        salonService.setCost(salonServiceRequest.getCost());
        salonService.setDescription(salonServiceRequest.getDescription());
        return salonService;
    }
}
