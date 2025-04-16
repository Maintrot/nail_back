package com.maintrot.basya.services.impl;

import com.maintrot.basya.dtoes.DependenceRequest;
import com.maintrot.basya.dtoes.DependenceResponse;
import com.maintrot.basya.mappers.DependenceMapper;
import com.maintrot.basya.models.Dependence;
import com.maintrot.basya.repositories.DependenceRepository;
import com.maintrot.basya.services.DependenceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DependenceServiceImpl implements DependenceService {

    private final DependenceMapper dependenceMapper;
    private final DependenceRepository dependenceRepository;

    public DependenceServiceImpl(DependenceMapper dependenceMapper, DependenceRepository dependenceRepository) {
        this.dependenceMapper = dependenceMapper;
        this.dependenceRepository = dependenceRepository;
    }

    @Override
    public DependenceResponse createDependence(DependenceRequest dependenceRequest) {
        Dependence dependence = dependenceMapper.toEntity(dependenceRequest);
        Dependence saveDependence = dependenceRepository.save(dependence);
        return dependenceMapper.toResponse(saveDependence);
    }

    @Override
    public DependenceResponse getDependence(Long id) {
        Dependence dependence = dependenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dependence not found"));
        return dependenceMapper.toResponse(dependence);
    }

    @Override
    public List<DependenceResponse> getAllDependencies () {
        List<Dependence> dependencies = dependenceRepository.findAll();
        return dependencies.stream()
                .map(dependenceMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DependenceResponse updateDependence(Long id, DependenceRequest dependenceRequest) {
        Dependence dependence = dependenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dependence not found"));
        if (dependenceRequest.getName() != null) {
            dependence.setName(dependenceRequest.getName());
        }
        if (dependenceRequest.getaBoolean() != null) {
            dependence.setaBoolean(dependenceRequest.getaBoolean());
        }
        if (dependenceRequest.getNumber() != 0) {
            dependence.setNumber(dependenceRequest.getNumber());
        }
        if (dependenceRequest.getText() != null) {
            dependence.setText(dependenceRequest.getText());
        }
        return dependenceMapper.toResponse(dependence);
    }

    @Override
    public void deleteDependence(Long id) {
        dependenceRepository.deleteById(id);
    }

    @Override
    public DependenceResponse getDependenceByName(String name) {
        Dependence dependence = dependenceRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Dependence not found"));
        return dependenceMapper.toResponse(dependence);
    }
}
