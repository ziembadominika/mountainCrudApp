package com.crudApp.mountain.repository;

import com.crudApp.mountain.domain.Mountain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MountainRepository extends JpaRepository<Mountain, Long> {
    List<Mountain> findByNameLike(String name);

}
