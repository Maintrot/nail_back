package com.maintrot.basya.repositories;

import com.maintrot.basya.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
