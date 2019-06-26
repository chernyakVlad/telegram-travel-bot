package com.chernayk.telegramtravelbot.repository;

import com.chernayk.telegramtravelbot.model.City;
import com.chernayk.telegramtravelbot.model.CityInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityInfoRepository extends JpaRepository<CityInfo, Long> {
    List<CityInfo> getCityInfoByCity(City city);
}
