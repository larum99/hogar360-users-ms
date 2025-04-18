package com.hogar360.users.users.infraestructure.repositories.mysql;

import com.hogar360.users.users.infraestructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
