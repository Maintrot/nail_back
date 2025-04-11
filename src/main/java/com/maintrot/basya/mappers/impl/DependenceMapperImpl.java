package com.maintrot.basya.mappers.impl;

import com.maintrot.basya.dtoes.DependenceRequest;
import com.maintrot.basya.dtoes.DependenceResponse;
import com.maintrot.basya.mappers.DependenceMapper;
import com.maintrot.basya.models.Dependence;
import org.springframework.stereotype.Component;

@Component
public class DependenceMapperImpl implements DependenceMapper {

    @Override
    public DependenceResponse toResponse(Dependence dependence) {
        DependenceResponse dependenceResponse = new DependenceResponse();
        dependenceResponse.setId(dependence.getId());
        dependenceResponse.setName(dependence.getName());
        dependenceResponse.setaBoolean(dependence.getaBoolean());
        dependenceResponse.setNumber(dependence.getNumber());
        dependenceResponse.setText(dependence.getText());
        return dependenceResponse;
    }

    @Override
    public Dependence toEntity(DependenceRequest dependenceRequest) {
        Dependence dependence = new Dependence();
        dependence.setName(dependenceRequest.getName());
        dependence.setaBoolean(dependenceRequest.getaBoolean());
        dependence.setNumber(dependenceRequest.getNumber());
        dependence.setText(dependenceRequest.getText());
        return dependence;
    }
}
