package com.maintrot.basya.repositories;

import com.maintrot.basya.models.Review;
import com.maintrot.basya.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByClient(User client);
}
