package com.maintrot.basya.services.impl;

import com.maintrot.basya.dtoes.MasterServiceRequest;
import com.maintrot.basya.dtoes.MasterServiceResponse;
import com.maintrot.basya.enums.Role;
import com.maintrot.basya.mappers.MasterServiceMapper;
import com.maintrot.basya.models.MasterService;
import com.maintrot.basya.models.SalonService;
import com.maintrot.basya.models.User;
import com.maintrot.basya.repositories.MasterServiceRepository;
import com.maintrot.basya.repositories.SalonServiceRepository;
import com.maintrot.basya.repositories.UserRepository;
import com.maintrot.basya.services.MasterServiceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MasterServiceServiceImpl implements MasterServiceService {

    private final MasterServiceMapper masterServiceMapper;
    private final MasterServiceRepository masterServiceRepository;
    private final UserRepository userRepository;
    private final SalonServiceRepository salonServiceRepository;

    public MasterServiceServiceImpl(MasterServiceMapper masterServiceMapper, MasterServiceRepository masterServiceRepository, UserRepository userRepository, SalonServiceRepository salonServiceRepository) {
        this.masterServiceMapper = masterServiceMapper;
        this.masterServiceRepository = masterServiceRepository;
        this.userRepository = userRepository;
        this.salonServiceRepository = salonServiceRepository;
    }

    @Override
    public MasterServiceResponse createMasterService(MasterServiceRequest masterServiceRequest) {
        User master = userRepository.findById(masterServiceRequest.getMasterId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!Role.USER_MASTER.equals(master.getRole())) {
            throw new RuntimeException("User is not a master");
        }
        SalonService salonService = salonServiceRepository.findById(masterServiceRequest.getSalonServiceId())
                .orElseThrow(() -> new RuntimeException("Salon service not found"));
        MasterService masterService = masterServiceMapper.toEntity(masterServiceRequest);
        masterService.setMaster(master);
        masterService.setSalonService(salonService);
        MasterService savedMasterService = masterServiceRepository.save(masterService);
        return masterServiceMapper.toResponse(savedMasterService);
    }

    @Override
    public MasterServiceResponse getMasterService(Long id) {
        MasterService masterService = masterServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Master service not found"));
        return masterServiceMapper.toResponse(masterService);
    }

    @Override
    public List<MasterServiceResponse> getAllMasterServices() {
        List<MasterService> masterServices = masterServiceRepository.findAll();
        return masterServices.stream()
                .map(masterServiceMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteMasterService(Long id) {
        masterServiceRepository.deleteById(id);
    }

    @Override
    public List<MasterServiceResponse> getMasterServicesByMasterName(String masterName) {
        User master = userRepository.findByName(masterName)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!Role.USER_MASTER.equals(master.getRole())) {
            throw new RuntimeException("User is not a master");
        }
        List<MasterService> masterServices = masterServiceRepository.findByMaster(master);
        return masterServices.stream()
                .map(masterServiceMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MasterServiceResponse> getMasterServicesBySalonServiceName(String salonServiceName) {
        SalonService salonService = salonServiceRepository.findByName(salonServiceName)
                .orElseThrow(() -> new RuntimeException("Salon service not found"));
        List<MasterService> masterServices = masterServiceRepository.findBySalonService(salonService);
        return masterServices.stream()
                .map(masterServiceMapper::toResponse)
                .collect(Collectors.toList());
    }
}
