package com.maintrot.basya.repositories;

import com.maintrot.basya.models.MasterService;
import com.maintrot.basya.models.SalonService;
import com.maintrot.basya.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MasterServiceRepository extends JpaRepository<MasterService, Long> {
    List<MasterService> findByMaster(User master);
    List<MasterService> findBySalonService(SalonService service);
}
