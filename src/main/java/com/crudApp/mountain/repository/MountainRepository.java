package com.crudApp.mountain.repository;

import com.crudApp.mountain.domain.Mountain;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface MountainRepository extends JpaRepository<Mountain, Long> {
    @Query("SELECT m FROM Mountain m WHERE m.name LIKE %:name%")
    Optional<List<Mountain>> searchByName(@Param("name") String name, Pageable pageable);
}
