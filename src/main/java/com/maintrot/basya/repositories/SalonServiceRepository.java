package com.maintrot.basya.repositories;

import com.maintrot.basya.models.SalonService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalonServiceRepository extends JpaRepository<SalonService, Long> {
    Optional<SalonService> findByName(String SalonServiceName);
}
