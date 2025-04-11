package com.maintrot.basya.services;

import com.maintrot.basya.dtoes.SalonServiceRequest;
import com.maintrot.basya.dtoes.SalonServiceResponse;

import java.util.List;

public interface SalonServiceService {
    SalonServiceResponse createSalonService(SalonServiceRequest salonServiceRequest);
    SalonServiceResponse getSalonService(Long id);
    List<SalonServiceResponse> getAllSalonServices();
    SalonServiceResponse updateSalonService(Long id, SalonServiceRequest salonServiceRequest);
    void deleteSalonService(Long id);
    SalonServiceResponse getSalonServiceByName(String salonServiceName);
}
