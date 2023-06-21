package com.crudApp.mountain.repository;

import com.crudApp.mountain.domain.MountainRange;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MountainRangeRepository extends JpaRepository<MountainRange, Long> {

    Optional<List<MountainRange>> findByRangeNameContainingIgnoreCase(String rangeName, Pageable pageable);
}
