package com.chernayk.telegramtravelbot.service;

import com.chernayk.telegramtravelbot.model.City;

import java.util.List;
import java.util.Optional;

public interface CityService {
    Optional<City> getCityById(Long id);
    Optional<City> getCityByName(String name);
    List<City> getAllCities();
    City saveCity(City city);
    void deleteCity(Long id);
}
