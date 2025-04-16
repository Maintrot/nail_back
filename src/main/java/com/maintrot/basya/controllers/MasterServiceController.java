package com.maintrot.basya.controllers;

import com.maintrot.basya.dtoes.MasterServiceRequest;
import com.maintrot.basya.dtoes.MasterServiceResponse;
import com.maintrot.basya.services.MasterServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/MasterServices")
@Tag(name = "Master Services API", description = "CRUD и не только операции для управления сервисами мастера")
public class MasterServiceController {

    private final MasterServiceService masterServiceService;

    public MasterServiceController(MasterServiceService masterServiceService) {
        this.masterServiceService = masterServiceService;
    }

    @Operation(summary = "Создать новый сервис мастера")
    @PostMapping
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_MASTER')")
    public ResponseEntity<MasterServiceResponse> createMasterService(MasterServiceRequest masterServiceRequest) {
        MasterServiceResponse masterServiceResponse = masterServiceService.createMasterService(masterServiceRequest);
        return new ResponseEntity<>(masterServiceResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Получить сервис мастера по ID (с данными мастера и сервиса салона)")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_MASTER')")
    public ResponseEntity<MasterServiceResponse> getMasterService(@PathVariable Long id) {
        MasterServiceResponse masterServiceResponse = masterServiceService.getMasterService(id);
        return ResponseEntity.ok(masterServiceResponse);
    }

    @Operation(summary = "Получить список всех сервисов мастеров")
    @GetMapping
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_MASTER', 'USER_CLIENT')")
    public ResponseEntity<List<MasterServiceResponse>> getAllMasterServices() {
        List<MasterServiceResponse> masterServiceResponses = masterServiceService.getAllMasterServices();
        return ResponseEntity.ok(masterServiceResponses);
    }

    @Operation(summary = "Удалить сервис мастера")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_MASTER')")
    public ResponseEntity<Void> deleteMasterService(@PathVariable Long id) {
        masterServiceService.deleteMasterService(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получить список сервисов мастеров по имени мастера")
    @GetMapping("/masterName/{masterName}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_MASTER', 'USER_CLIENT')")
    public ResponseEntity<List<MasterServiceResponse>> getMasterServicesByMasterName(@PathVariable String masterName) {
        List<MasterServiceResponse> masterServiceResponses = masterServiceService.getMasterServicesByMasterName(masterName);
        return ResponseEntity.ok(masterServiceResponses);
    }

    @Operation(summary = "Получить список сервисов мастеров по имени сервиса")
    @GetMapping("/serviceName/{serviceName}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_MASTER', 'USER_CLIENT')")
    public ResponseEntity<List<MasterServiceResponse>> getMasterServicesBySalonServiceName(@PathVariable String serviceName) {
        List<MasterServiceResponse> masterServiceResponses = masterServiceService.getMasterServicesBySalonServiceName(serviceName);
        return ResponseEntity.ok(masterServiceResponses);
    }
}
