package com.maintrot.basya.mappers.impl;

import com.maintrot.basya.dtoes.ReviewRequest;
import com.maintrot.basya.dtoes.ReviewResponse;
import com.maintrot.basya.mappers.ReviewMapper;
import com.maintrot.basya.models.Review;
import com.maintrot.basya.models.User;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public ReviewResponse toResponse(Review review) {
        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setId(review.getId());
        reviewResponse.setClientId(review.getClient().getId());
        reviewResponse.setText(review.getText());
        reviewResponse.setRating(review.getRating());
        return reviewResponse;
    }

    @Override
    public Review toEntity(ReviewRequest reviewRequest) {
        Review review = new Review();
        User client = new User();
        client.setId(reviewRequest.getClientId());
        review.setClient(client);
        review.setText(reviewRequest.getText());
        review.setRating(reviewRequest.getRating());
        return review;
    }
}
