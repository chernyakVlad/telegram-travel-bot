package com.chernayk.telegramtravelbot.controller;

import com.chernayk.telegramtravelbot.model.City;
import com.chernayk.telegramtravelbot.model.CityInfo;
import com.chernayk.telegramtravelbot.service.CityInfoService;
import com.chernayk.telegramtravelbot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/citiesInfo")
public class CityInfoController {

    private CityInfoService cityInfoService;
    private CityService cityService;


    @Autowired
    public CityInfoController(CityInfoService cityInfoService, CityService cityService) {
        this.cityInfoService = cityInfoService;
        this.cityService = cityService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CityInfo> getCityInfoById(@PathVariable Long id) {

        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<CityInfo> result = cityInfoService.getCityInfoById(id);

        if(result.isPresent()){
            return new ResponseEntity<CityInfo>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/city/{name}")
    public ResponseEntity<List<CityInfo>> getCityById(@PathVariable String name) {

        Optional<City> city = cityService.getCityByName(name);
        List<CityInfo> result = cityInfoService.getAllByCity(city.get());

        if(result != null){
            return new ResponseEntity<List<CityInfo>>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<CityInfo> saveCity(@RequestBody CityInfo cityInfo) {

        if(cityInfo == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<CityInfo>(cityInfoService.saveCityInfo(cityInfo), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<City> deleteCity(@PathVariable Long id) {

        if(id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        cityInfoService.deleteCityInfo(id);

        return new ResponseEntity<City>(HttpStatus.OK);
    }
}
