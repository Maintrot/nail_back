package com.maintrot.basya.repositories;

import com.maintrot.basya.enums.Role;
import com.maintrot.basya.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    boolean existsByName(String name);
    boolean existsByPhone(String phone);
    List<User> findByRole(Role role);
    Optional<User> findByPhone(String phone);
}
