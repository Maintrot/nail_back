package com.maintrot.basya.controllers;

import com.maintrot.basya.dtoes.ReviewRequest;
import com.maintrot.basya.dtoes.ReviewResponse;
import com.maintrot.basya.services.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Reviews")
@Tag(name = "Review API", description = "CRUD и не только операции для управления отзывами")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Operation(summary = "Создать новый отзыв")
    @PostMapping
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_CLIENT')")
    public ResponseEntity<ReviewResponse> createReview(@RequestBody ReviewRequest reviewRequest) {
        ReviewResponse reviewResponse = reviewService.createReview(reviewRequest);
        return new ResponseEntity<>(reviewResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Получить отзыв по ID (с данными пользователя)")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_CLIENT')")
    public ResponseEntity<ReviewResponse> getReview(@PathVariable Long id) {
        ReviewResponse reviewResponse = reviewService.getReview(id);
        return ResponseEntity.ok(reviewResponse);
    }

    @Operation(summary = "Получить список всех отзывов")
    @GetMapping
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_CLIENT')")
    public ResponseEntity<List<ReviewResponse>> getAllReviews() {
        List<ReviewResponse> reviewResponses = reviewService.getAllReviews();
        return ResponseEntity.ok(reviewResponses);
    }

    @Operation(summary = "Обновить данные отзыва")
    @PostMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_CLIENT')")
    public ResponseEntity<ReviewResponse> updateReview(@PathVariable Long id, @RequestBody ReviewRequest reviewRequest) {
        ReviewResponse reviewResponse = reviewService.updateReview(id, reviewRequest);
        return ResponseEntity.ok(reviewResponse);
    }

    @Operation(summary = "Удалить отзыв")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_CLIENT')")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получить список отзывов клиента по имени клиента")
    @GetMapping("/clientName/{clientName}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_CLIENT')")
    public ResponseEntity<List<ReviewResponse>> getReviewsByClientName(@PathVariable String clientName) {
        List<ReviewResponse> reviewResponses = reviewService.getReviewsByClientName(clientName);
        return ResponseEntity.ok(reviewResponses);
    }
}
