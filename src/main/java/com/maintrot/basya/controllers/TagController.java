package com.maintrot.basya.controllers;

import com.maintrot.basya.dtoes.TagRequest;
import com.maintrot.basya.dtoes.TagResponse;
import com.maintrot.basya.services.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
@Tag(name = "Tags API", description = "CRUD и не только операции для управлениями тегами")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @Operation(summary = "Создать новый тег")
    @PostMapping
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_MASTER')")
    public ResponseEntity<TagResponse> createTag(@RequestBody TagRequest tagRequest) {
        TagResponse tagResponse = tagService.createTag(tagRequest);
        return new ResponseEntity<>(tagResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Получить тег по ID (с данными о пользователях)")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_MASTER')")
    public ResponseEntity<TagResponse> getTag(@PathVariable Long id) {
        TagResponse tagResponse = tagService.getTag(id);
        return ResponseEntity.ok(tagResponse);
    }

    @Operation(summary = "Получить список всех теги")
    @GetMapping
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_MASTER')")
    public ResponseEntity<List<TagResponse>> getAllTag() {
        List<TagResponse> tagResponses = tagService.getAllTags();
        return ResponseEntity.ok(tagResponses);
    }

    @Operation(summary = "Обновить данные тега")
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_MASTER')")
    public ResponseEntity<TagResponse> updateTag(@PathVariable Long id, @RequestBody TagRequest tagRequest) {
        TagResponse tagResponse = tagService.updateTag(id, tagRequest);
        return ResponseEntity.ok(tagResponse);
    }

    @Operation(summary = "Удалить тег")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_MASTER')")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получить список тегов по имени клиента")
    @GetMapping("/client/{clientName}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_MASTER')")
    public ResponseEntity<List<TagResponse>> getTagsByClientName(@PathVariable String clientName) {
        List<TagResponse> tagResponses = tagService.getTagsByClientName(clientName);
        return ResponseEntity.ok(tagResponses);
    }

    @Operation(summary = "Получить список тегов по имени мастера")
    @GetMapping("/master/{masterName}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_MASTER')")
    public ResponseEntity<List<TagResponse>> getTagsByMasterName(@PathVariable String masterName) {
        List<TagResponse> tagResponses = tagService.getTagsByMasterName(masterName);
        return ResponseEntity.ok(tagResponses);
    }

}
