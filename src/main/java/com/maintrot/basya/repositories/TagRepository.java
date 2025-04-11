package com.maintrot.basya.repositories;

import com.maintrot.basya.models.Tag;
import com.maintrot.basya.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByClient(User client);
    List<Tag> findByMaster(User master);
}
