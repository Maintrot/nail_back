package com.maintrot.basya.controllers;

import com.maintrot.basya.dtoes.ScheduleRequest;
import com.maintrot.basya.dtoes.ScheduleResponse;
import com.maintrot.basya.services.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Schedules")
@Tag(name = "Schedules API", description = "CRUD и не только операции для управления расписанием")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Operation(summary = "Создать новое расписание")
    @PostMapping
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_MASTER')")
    public ResponseEntity<ScheduleResponse> createSchedule(ScheduleRequest scheduleRequest) {
        ScheduleResponse scheduleResponse = scheduleService.createSchedule(scheduleRequest);
        return new ResponseEntity<>(scheduleResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Получить расписание по ID (с данными о мастере)")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_MASTER')")
    public ResponseEntity<ScheduleResponse> getSchedule(@PathVariable Long id) {
        ScheduleResponse scheduleResponse = scheduleService.getSchedule(id);
        return ResponseEntity.ok(scheduleResponse);
    }

    @Operation(summary = "Получить список всех расписаний")
    @GetMapping
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_MASTER')")
    public ResponseEntity<List<ScheduleResponse>> getAllSchedules() {
        List<ScheduleResponse> scheduleResponses = scheduleService.getAllSchedules();
        return ResponseEntity.ok(scheduleResponses);
    }

    @Operation(summary = "Обновить данные расписания")
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_MASTER')")
    public ResponseEntity<ScheduleResponse> updateSchedule(@PathVariable Long id, ScheduleRequest scheduleRequest) {
        ScheduleResponse scheduleResponse = scheduleService.updateSchedule(id, scheduleRequest);
        return ResponseEntity.ok(scheduleResponse);
    }

    @Operation(summary = "Удалить расписание")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_MASTER')")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получить список расписаний по имени мастера")
    @GetMapping("/masterName/{masterName}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_MASTER')")
    public ResponseEntity<List<ScheduleResponse>> getSchedulesByMasterName(@PathVariable String masterName) {
        List<ScheduleResponse> scheduleResponses = scheduleService.getSchedulesByMasterName(masterName);
        return ResponseEntity.ok(scheduleResponses);
    }

    @Operation(summary = "Получить список расписаний по номеру мастера")
    @GetMapping("/masterPhone/{masterPhone}")
    @PreAuthorize("hasRole('USER_ADMIN')")
    public ResponseEntity<List<ScheduleResponse>> getSchedulesByMasterPhone(@PathVariable String masterPhone) {
        List<ScheduleResponse> scheduleResponses = scheduleService.getSchedulesByMasterPhone(masterPhone);
        return ResponseEntity.ok(scheduleResponses);
    }

    @Operation(summary = "Получить список расписаний по дню недели")
    @GetMapping("/day/{day}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_MASTER')")
    public ResponseEntity<List<ScheduleResponse>> getSchedulesByWeekDay(@PathVariable String weekDay) {
        List<ScheduleResponse> scheduleResponses = scheduleService.getSchedulesByWeekDay(weekDay);
        return ResponseEntity.ok(scheduleResponses);
    }

}
