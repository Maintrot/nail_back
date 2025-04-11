package com.maintrot.basya.services.impl;

import com.maintrot.basya.dtoes.ReviewRequest;
import com.maintrot.basya.dtoes.ReviewResponse;
import com.maintrot.basya.mappers.ReviewMapper;
import com.maintrot.basya.models.Review;
import com.maintrot.basya.models.User;
import com.maintrot.basya.repositories.ReviewRepository;
import com.maintrot.basya.repositories.UserRepository;
import com.maintrot.basya.services.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public ReviewServiceImpl(ReviewMapper reviewMapper, ReviewRepository reviewRepository, UserRepository userRepository) {
        this.reviewMapper = reviewMapper;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ReviewResponse createReview(ReviewRequest reviewRequest) {
        User client = userRepository.findById(reviewRequest.getClientId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!"USER_CLIENT".equals(client.getRole())) {
            throw new RuntimeException("User is not a client");
        }
        Review review = reviewMapper.toEntity(reviewRequest);
        review.setClient(client);
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toResponse(savedReview);
    }

    @Override
    public ReviewResponse getReview (Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review is not found"));
        return reviewMapper.toResponse(review);
    }

    @Override
    public List<ReviewResponse> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream()
                .map(reviewMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewResponse updateReview(Long id, ReviewRequest reviewRequest) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review is not found"));
        review.setText(reviewRequest.getText());
        review.setRating(reviewRequest.getRating());
        return reviewMapper.toResponse(review);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
