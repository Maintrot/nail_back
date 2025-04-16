package com.maintrot.basya.repositories;

import com.maintrot.basya.models.Dependence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DependenceRepository extends JpaRepository<Dependence, Long> {
    Optional<Dependence> findByName(String name);
}
