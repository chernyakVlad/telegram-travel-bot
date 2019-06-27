package com.chernayk.telegramtravelbot.repository;

import com.chernayk.telegramtravelbot.model.City;
import com.chernayk.telegramtravelbot.model.CityInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityInfoRepository extends JpaRepository<CityInfo, Long> {
    Optional<CityInfo> getById(Long id);
    List<CityInfo> getAllByCityId(Long id);
}
