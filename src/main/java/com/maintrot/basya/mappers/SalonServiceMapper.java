package com.maintrot.basya.mappers;

import com.maintrot.basya.dtoes.SalonServiceRequest;
import com.maintrot.basya.dtoes.SalonServiceResponse;
import com.maintrot.basya.models.SalonService;

public interface SalonServiceMapper {
    SalonServiceResponse toResponce(SalonService salonService);
    SalonService toEntity(SalonServiceRequest salonServiceRequest);
}
