package com.test.repository;

import com.test.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICountryRepository extends JpaRepository<Country,Integer> {
    @Query(value = "SELECT * FROM city WHERE name LIKE %?1% OR LIKE area %?1%;", nativeQuery = true)
    Iterable<Country> searchByNamec(String name);
}
