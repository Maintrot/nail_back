package com.maintrot.basya.controllers;

import com.maintrot.basya.dtoes.ScheduleRequest;
import com.maintrot.basya.dtoes.ScheduleResponse;
import com.maintrot.basya.services.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Schedules")
@Tag(name = "Schedules API", description = "CRUD операции для управления расписанием")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Operation(summary = "Создать новое расписание")
    @PostMapping
    public ResponseEntity<ScheduleResponse> createSchedule(ScheduleRequest scheduleRequest) {
        ScheduleResponse scheduleResponse = scheduleService.createSchedule(scheduleRequest);
        return new ResponseEntity<>(scheduleResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Получить расписание по ID (с данными о мастере)")
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> getSchedule(@PathVariable Long id) {
        ScheduleResponse scheduleResponse = scheduleService.getSchedule(id);
        return ResponseEntity.ok(scheduleResponse);
    }

    @Operation(summary = "Получить список всех расписаний")
    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> getAllSchedules() {
        List<ScheduleResponse> scheduleResponses = scheduleService.getAllSchedules();
        return ResponseEntity.ok(scheduleResponses);
    }

    @Operation(summary = "Обновить данные расписания")
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponse> updateSchedule(@PathVariable Long id, ScheduleRequest scheduleRequest) {
        ScheduleResponse scheduleResponse = scheduleService.updateSchedule(id, scheduleRequest);
        return ResponseEntity.ok(scheduleResponse);
    }

    @Operation(summary = "Удалить расписание")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
}
