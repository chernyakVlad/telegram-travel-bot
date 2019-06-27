package com.chernayk.telegramtravelbot.repository;

import com.chernayk.telegramtravelbot.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> getByName(String name);
    Optional<City> getById(Long id);
}
