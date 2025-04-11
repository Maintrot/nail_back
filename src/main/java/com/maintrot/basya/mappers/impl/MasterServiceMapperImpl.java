package com.maintrot.basya.mappers.impl;

import com.maintrot.basya.dtoes.MasterServiceRequest;
import com.maintrot.basya.dtoes.MasterServiceResponse;
import com.maintrot.basya.mappers.MasterServiceMapper;
import com.maintrot.basya.models.MasterService;
import com.maintrot.basya.models.SalonService;
import com.maintrot.basya.models.User;
import org.springframework.stereotype.Component;

@Component
public class MasterServiceMapperImpl implements MasterServiceMapper {

    @Override
    public MasterServiceResponse toResponse(MasterService masterService) {
        MasterServiceResponse masterServiceResponse = new MasterServiceResponse();
        masterServiceResponse.setId(masterService.getId());
        masterServiceResponse.setMasterId(masterService.getMaster().getId());
        masterServiceResponse.setSalonServiceId(masterService.getSalonService().getId());
        return masterServiceResponse;
    }

    @Override
    public MasterService toEntity(MasterServiceRequest masterServiceRequest) {
        MasterService masterService = new MasterService();
        User master = new User();
        master.setId(masterServiceRequest.getMasterId());
        masterService.setMaster(master);
        SalonService salonService = new SalonService();
        salonService.setId(masterServiceRequest.getSalonServiceId());
        masterService.setSalonService(salonService);
        return masterService;
    }
}
