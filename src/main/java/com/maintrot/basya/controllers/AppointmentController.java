package com.maintrot.basya.controllers;

import com.maintrot.basya.dtoes.AppointmentRequest;
import com.maintrot.basya.dtoes.AppointmentResponse;
import com.maintrot.basya.services.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@Tag(name = "appointments API", description = "CRUD и не только операции для управления записями")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Operation(summary = "Создать новую запись")
    @PostMapping
    public ResponseEntity<AppointmentResponse> createAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        AppointmentResponse appointmentResponse = appointmentService.createAppointment(appointmentRequest);
        return new ResponseEntity<>(appointmentResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "получить запись по ID (с данными мастера и клиента)")
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> getAppointment(@PathVariable Long id) {
        AppointmentResponse appointmentResponse = appointmentService.getAppointment(id);
        return ResponseEntity.ok(appointmentResponse);
    }

    @Operation(summary = "Получить список всех записей")
    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {
        List<AppointmentResponse> appointmentResponses = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointmentResponses);
    }

    @Operation(summary = "Обновить данные записи")
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponse> updateAppointment(@PathVariable Long id, @RequestBody AppointmentRequest appointmentRequest) {
        AppointmentResponse appointmentResponse = appointmentService.updateAppointment(id, appointmentRequest);
        return ResponseEntity.ok(appointmentResponse);
    }

    @Operation(summary = "Удалить запись")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
