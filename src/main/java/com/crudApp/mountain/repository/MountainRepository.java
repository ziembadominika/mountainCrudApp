package com.crudApp.mountain.repository;

import com.crudApp.mountain.domain.Mountain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MountainRepository extends JpaRepository<Mountain, Long> {
//    @Query
    List<Mountain> findByNameContaining(String name);
}
