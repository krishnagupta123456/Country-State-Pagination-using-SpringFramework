package com.country_pagination.Repository;

import com.country_pagination.Model.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
    State findByName(String name);
}
