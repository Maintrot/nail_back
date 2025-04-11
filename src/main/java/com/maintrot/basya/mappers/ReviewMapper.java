package com.maintrot.basya.mappers;

import com.maintrot.basya.dtoes.ReviewRequest;
import com.maintrot.basya.dtoes.ReviewResponse;
import com.maintrot.basya.models.Review;
import org.springframework.http.ResponseEntity;

public interface ReviewMapper {
    ReviewResponse toResponse(Review review);
    Review toEntity(ReviewRequest reviewRequest);
}
