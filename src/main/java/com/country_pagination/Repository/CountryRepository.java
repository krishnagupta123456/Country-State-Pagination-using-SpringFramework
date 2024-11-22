package com.country_pagination.Repository;

import com.country_pagination.Model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Long> {
    Country findByName(String name);
}
