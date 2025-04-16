package com.maintrot.basya.controllers;

import com.maintrot.basya.dtoes.SalonServiceRequest;
import com.maintrot.basya.dtoes.SalonServiceResponse;
import com.maintrot.basya.services.SalonServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salon_services")
@Tag(name = "Salon services API", description = "CRUD и не только операции для управления сервисами пользователя")
public class SalonServiceController {

    private final SalonServiceService salonServiceService;

    public SalonServiceController(SalonServiceService salonServiceService) {
        this.salonServiceService = salonServiceService;
    }

    @Operation(summary = "Создать новый сервис салона")
    @PostMapping
    @PreAuthorize("hasRole('USER_ADMIN')")
    public ResponseEntity<SalonServiceResponse> createSalonService(@RequestBody SalonServiceRequest salonServiceRequest) {
        SalonServiceResponse salonServiceResponse = salonServiceService.createSalonService(salonServiceRequest);
        return new ResponseEntity<>(salonServiceResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Получить сервис салона по ID")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_MASTER')")
    public ResponseEntity<SalonServiceResponse> getSalonService(@PathVariable Long id) {
        SalonServiceResponse salonServiceResponse = salonServiceService.getSalonService(id);
        return ResponseEntity.ok(salonServiceResponse);
    }

    @Operation(summary = "Получить список всех сервисов салона")
    @GetMapping
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_MASTER')")
    public ResponseEntity<List<SalonServiceResponse>> getAllSalonservices() {
        List<SalonServiceResponse> salonServiceResponses = salonServiceService.getAllSalonServices();
        return ResponseEntity.ok(salonServiceResponses);
    }

    @Operation(summary = "Обновить данные сервиса салона")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER_ADMIN')")
    public ResponseEntity<SalonServiceResponse> updateServiceSalon(@PathVariable Long id, @RequestBody SalonServiceRequest salonServiceRequest) {
        SalonServiceResponse salonServiceResponse = salonServiceService.updateSalonService(id, salonServiceRequest);
        return ResponseEntity.ok(salonServiceResponse);
    }

    @Operation(summary = "Удалить сервис салона")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER_ADMIN')")
    public ResponseEntity<SalonServiceResponse> deleteSalonService(@PathVariable Long id) {
        salonServiceService.deleteSalonService(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получить сервис салона по имени сервиса")
    @GetMapping("/name/{name}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_MASTER')")
    public ResponseEntity<SalonServiceResponse> getSalonServiceByName(@PathVariable String name) {
        SalonServiceResponse salonServiceResponse = salonServiceService.getSalonServiceByName(name);
        return ResponseEntity.ok(salonServiceResponse);
    }
}
