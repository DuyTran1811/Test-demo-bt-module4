package com.test.repository;

import com.test.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICityRepository extends JpaRepository<City, Integer> {
    @Query(value = "SELECT * FROM city WHERE name LIKE ?1", nativeQuery = true)
    Iterable<City> searchByName(String name);
}
