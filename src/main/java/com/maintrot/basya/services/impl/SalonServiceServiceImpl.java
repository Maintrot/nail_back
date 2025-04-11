package com.maintrot.basya.services.impl;

import com.maintrot.basya.dtoes.SalonServiceRequest;
import com.maintrot.basya.dtoes.SalonServiceResponse;
import com.maintrot.basya.mappers.SalonServiceMapper;
import com.maintrot.basya.models.SalonService;
import com.maintrot.basya.repositories.SalonServiceRepository;
import com.maintrot.basya.services.SalonServiceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalonServiceServiceImpl implements SalonServiceService {
    private final SalonServiceMapper salonServiceMapper;
    private final SalonServiceRepository salonServiceRepository;

    public SalonServiceServiceImpl (SalonServiceRepository salonServiceRepository, SalonServiceMapper salonServiceMapper) {
        this.salonServiceMapper = salonServiceMapper;
        this.salonServiceRepository = salonServiceRepository;
    }

    @Override
    public SalonServiceResponse createSalonService(SalonServiceRequest salonServiceRequest) {
        SalonService salonService = salonServiceMapper.toEntity(salonServiceRequest);
        SalonService saveSalonService = salonServiceRepository.save(salonService);
        return salonServiceMapper.toResponce(saveSalonService);
    }

    @Override
    public SalonServiceResponse getSalonService(Long id) {
        SalonService salonService = salonServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salon service not found"));
        return salonServiceMapper.toResponce(salonService);
    }

    @Override
    public List<SalonServiceResponse> getAllSalonServices() {
        List<SalonService> salonServices = salonServiceRepository.findAll();
        return salonServices.stream()
                .map(salonServiceMapper::toResponce)
                .collect(Collectors.toList());
    }

    @Override
    public SalonServiceResponse updateSalonService(Long id, SalonServiceRequest salonServiceRequest) {
        SalonService salonService = salonServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salon service not found"));
        salonService.setName(salonServiceRequest.getName());
        salonService.setCost(salonServiceRequest.getCost());
        salonService.setDescription(salonServiceRequest.getDescription());
        SalonService updatedSalonService = salonServiceRepository.save(salonService);
        return salonServiceMapper.toResponce(updatedSalonService);
    }

    @Override
    public void deleteSalonService(Long id) {
        salonServiceRepository.deleteById(id);
    }
}
