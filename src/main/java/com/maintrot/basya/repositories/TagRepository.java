package com.maintrot.basya.repositories;

import com.maintrot.basya.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
