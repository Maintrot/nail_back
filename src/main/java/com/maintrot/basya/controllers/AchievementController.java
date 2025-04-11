package com.maintrot.basya.controllers;

import com.maintrot.basya.dtoes.AchievementRequest;
import com.maintrot.basya.dtoes.AchievementResponse;
import com.maintrot.basya.services.AchievementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/achievements")
@Tag(name = "Achievement API", description = "CRUD операции для управления ачивками")
public class AchievementController {

    private final AchievementService achievementService;

    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @Operation(summary = "Создать новую ачивку")
    @PostMapping
    public ResponseEntity<AchievementResponse> createAchievement(@RequestBody AchievementRequest achievementRequest) {
        AchievementResponse achievementResponse = achievementService.createAchievement(achievementRequest);
        return new ResponseEntity<>(achievementResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Получить ачивку по ID (с данными пользователя)")
    @GetMapping("/{id}")
    public ResponseEntity<AchievementResponse> getAchievement(@PathVariable Long id) {
        AchievementResponse achievementResponse = achievementService.getAchievement(id);
        return ResponseEntity.ok(achievementResponse);
    }

    @Operation(summary = "Получить список всех ачивок")
    @GetMapping
    public ResponseEntity<List<AchievementResponse>> getAllAchievements() {
        List<AchievementResponse> achievementResponses = achievementService.getAllAchievements();
        return ResponseEntity.ok(achievementResponses);
    }

    @Operation(summary = "Обновить данные ачивок")
    @PutMapping("/{id}")
    public ResponseEntity updateAchievement(@PathVariable Long id, @RequestBody AchievementRequest achievementRequest) {
        AchievementResponse achievementResponse = achievementService.updateAchievement(id, achievementRequest);
        return ResponseEntity.ok(achievementResponse);
    }

    @Operation(summary = "Удалить ачивку")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAchievement(@PathVariable Long id) {
        achievementService.deleteAchievement(id);
        return ResponseEntity.noContent().build();
    }
}
