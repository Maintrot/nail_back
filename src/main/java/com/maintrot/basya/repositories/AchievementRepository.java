package com.maintrot.basya.repositories;

import com.maintrot.basya.models.Achievement;
import com.maintrot.basya.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    List<Achievement> findByClient(User client);
    List<Achievement> findByName(String name);
}
