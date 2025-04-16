package com.maintrot.basya.services;

import com.maintrot.basya.dtoes.DependenceRequest;
import com.maintrot.basya.dtoes.DependenceResponse;
import com.maintrot.basya.models.Dependence;

import java.util.List;

public interface DependenceService {
    DependenceResponse createDependence(DependenceRequest dependenceRequest);
    DependenceResponse getDependence(Long id);
    List<DependenceResponse> getAllDependencies();
    DependenceResponse updateDependence(Long id, DependenceRequest dependenceRequest);
    void deleteDependence(Long id);
    DependenceResponse getDependenceByName(String name);

}
