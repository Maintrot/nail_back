package com.maintrot.basya.controllers;

import com.maintrot.basya.dtoes.DependenceRequest;
import com.maintrot.basya.dtoes.DependenceResponse;
import com.maintrot.basya.services.DependenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/dependencies")
@Tag(name = "Dependencies API", description = "CRUD и не только операции для управления зависимостями")
public class DependenceController {

    private final DependenceService dependenceService;

    public DependenceController(DependenceService dependenceService) {
        this.dependenceService = dependenceService;
    }

    @Operation(summary = "Создать новую зависимость")
    @PostMapping
    @PreAuthorize("hasRole('USER_ADMIN')")
    public ResponseEntity<DependenceResponse> createDependence(@RequestBody DependenceRequest dependenceRequest) {
        DependenceResponse dependenceResponse = dependenceService.createDependence(dependenceRequest);
        return new ResponseEntity<>(dependenceResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Получить зависимость по ID")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER_ADMIN')")
    public ResponseEntity<DependenceResponse> getDependence(@PathVariable Long id) {
        DependenceResponse dependenceResponse = dependenceService.getDependence(id);
        return ResponseEntity.ok(dependenceResponse);
    }

    @Operation(summary = "Получить список всех зависимостей")
    @GetMapping
    @PreAuthorize("hasRole('USER_ADMIN')")
    public ResponseEntity<List<DependenceResponse>> getAllDependencies() {
        List<DependenceResponse> dependenceResponses = dependenceService.getAllDependencies();
        return ResponseEntity.ok(dependenceResponses);
    }

    @Operation(summary = "Обновить данные зависимости")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER_ADMIN')")
    public ResponseEntity<DependenceResponse> updateDependence(@PathVariable Long id, @RequestBody DependenceRequest dependenceRequest) {
        DependenceResponse dependenceResponse = dependenceService.updateDependence(id, dependenceRequest);
        return ResponseEntity.ok(dependenceResponse);
    }

    @Operation(summary = "Удалить зависимость")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER_ADMIN')")
    public ResponseEntity<DependenceResponse> deleteDependence(@PathVariable Long id) {
        dependenceService.deleteDependence(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получить зависимость по имени")
    @GetMapping("/name/{name}")
    @PreAuthorize("hasRole('USER_ADMIN')")
    public ResponseEntity<DependenceResponse> getDependenceByName(@PathVariable String name) {
        DependenceResponse dependenceResponse = dependenceService.getDependenceByName(name);
        return ResponseEntity.ok(dependenceResponse);
    }
}
