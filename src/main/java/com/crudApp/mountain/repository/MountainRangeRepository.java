package com.crudApp.mountain.repository;

import com.crudApp.mountain.domain.MountainRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MountainRangeRepository extends JpaRepository<MountainRange, Long> {

    List<MountainRange> findByRangeNameLike(String mountainRangeName);
}
