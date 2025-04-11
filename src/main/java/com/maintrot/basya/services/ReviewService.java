package com.maintrot.basya.services;

import com.maintrot.basya.dtoes.ReviewRequest;
import com.maintrot.basya.dtoes.ReviewResponse;

import java.util.List;

public interface ReviewService {
    ReviewResponse createReview(ReviewRequest reviewRequest);
    ReviewResponse getReview(Long id);
    List<ReviewResponse> getAllReviews();
    ReviewResponse updateReview(Long id, ReviewRequest reviewRequest);
    void deleteReview(Long id);
}
