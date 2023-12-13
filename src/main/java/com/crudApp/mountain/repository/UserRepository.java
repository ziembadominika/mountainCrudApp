package com.crudApp.mountain.repository;

import com.crudApp.mountain.domain.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserName(String username);
    Boolean existsByUserName(String userName);
    Optional<List<UserEntity>> findByUserNameContaining(String username, Pageable pageable);
}
