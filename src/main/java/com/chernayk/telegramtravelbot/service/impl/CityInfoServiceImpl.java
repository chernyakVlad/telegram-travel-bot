package com.chernayk.telegramtravelbot.service.impl;

import com.chernayk.telegramtravelbot.model.City;
import com.chernayk.telegramtravelbot.model.CityInfo;
import com.chernayk.telegramtravelbot.repository.CityInfoRepository;
import com.chernayk.telegramtravelbot.repository.CityRepository;
import com.chernayk.telegramtravelbot.service.CityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityInfoServiceImpl implements CityInfoService {

    private CityInfoRepository cityInfoRepository;

    @Autowired
    public CityInfoServiceImpl(CityInfoRepository cityInfoRepository) {
        this.cityInfoRepository = cityInfoRepository;
    }

    @Override
    public Optional<CityInfo> getCityInfoById(Long id) {
        return cityInfoRepository.getById(id);
    }

    @Override
    public List<CityInfo> getAllByCity(City city) {
        return cityInfoRepository.getAllByCityId(city.getId());
    }

    @Override
    public CityInfo saveCityInfo(CityInfo cityInfo) {
        return cityInfoRepository.save(cityInfo);
    }

    @Override
    public void deleteCityInfo(Long id) {
        cityInfoRepository.deleteById(id);
    }
}
