package com.crudApp.mountain.repository;

import com.crudApp.mountain.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserName(String userName);
    Boolean existsByUserName(String userName);
}
