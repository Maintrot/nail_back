package com.maintrot.basya.services;

import com.maintrot.basya.dtoes.MasterServiceRequest;
import com.maintrot.basya.dtoes.MasterServiceResponse;

import java.util.List;

public interface MasterServiceService {
    MasterServiceResponse createMasterService(MasterServiceRequest masterServiceRequest);
    MasterServiceResponse getMasterService(Long id);
    List<MasterServiceResponse> getAllMasterServices();
    void deleteMasterService(Long id);
}
