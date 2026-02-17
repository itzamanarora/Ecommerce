package com.aman.Ecommerce.user.repository;

import com.aman.Ecommerce.user.entity.User;
import com.aman.Ecommerce.user.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    List<User> findByStatus(String status);
}
