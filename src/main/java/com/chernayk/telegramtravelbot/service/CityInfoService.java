package com.chernayk.telegramtravelbot.service;

import com.chernayk.telegramtravelbot.model.City;
import com.chernayk.telegramtravelbot.model.CityInfo;

import java.util.List;
import java.util.Optional;

public interface CityInfoService  {
    Optional<CityInfo> getCityInfoById(Long id);
    List<CityInfo> getAllByCity(City city);
    CityInfo saveCityInfo(CityInfo cityInfo);
    void deleteCityInfo(Long id);
}
