package com.crudApp.mountain.repository;

import com.crudApp.mountain.domain.Continent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContinentRepository extends JpaRepository<Continent, Long> {
    List<Continent>findByContinentNameLike(String name);
}
