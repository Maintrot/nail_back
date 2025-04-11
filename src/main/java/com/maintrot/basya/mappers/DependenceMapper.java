package com.maintrot.basya.mappers;

import com.maintrot.basya.dtoes.DependenceRequest;
import com.maintrot.basya.dtoes.DependenceResponse;
import com.maintrot.basya.models.Dependence;

public interface DependenceMapper {
    DependenceResponse toResponse(Dependence dependence);
    Dependence toEntity(DependenceRequest dependenceRequest);
}
