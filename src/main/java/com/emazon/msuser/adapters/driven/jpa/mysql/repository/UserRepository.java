package com.emazon.msuser.adapters.driven.jpa.mysql.repository;

import com.emazon.msuser.adapters.driven.jpa.mysql.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByDocumentNumber(String document);
}
