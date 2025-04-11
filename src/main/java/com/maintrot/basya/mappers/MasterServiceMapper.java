package com.maintrot.basya.mappers;

import com.maintrot.basya.dtoes.MasterServiceRequest;
import com.maintrot.basya.dtoes.MasterServiceResponse;
import com.maintrot.basya.models.MasterService;

public interface MasterServiceMapper {
    MasterServiceResponse toResponse(MasterService masterService);
    MasterService toEntity(MasterServiceRequest masterServiceRequest);
}
