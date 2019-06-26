package com.chernayk.telegramtravelbot.handler.impl;

import com.chernayk.telegramtravelbot.handler.TelegramMessageHandler;
import com.chernayk.telegramtravelbot.model.City;
import com.chernayk.telegramtravelbot.model.CityInfo;
import com.chernayk.telegramtravelbot.repository.CityInfoRepository;
import com.chernayk.telegramtravelbot.repository.CityRepository;
import com.chernayk.telegramtravelbot.service.TravelBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Optional;

@Component
public class CityTelegramMessageHandler implements TelegramMessageHandler {

    private TravelBot travelBot;
    private CityRepository cityRepository;
    private CityInfoRepository cityInfoRepository;

    @Autowired
    public CityTelegramMessageHandler(TravelBot travelBot, CityInfoRepository cityInfoRepository, CityRepository cityRepository) {
        this.travelBot = travelBot;
        this.cityRepository = cityRepository;
        this.cityInfoRepository = cityInfoRepository;
    }

    @Override
    public void handle(Update update) {
        if(update.getMessage().getText().startsWith(TravelBot.HELP_BUTTON)
            || update.getMessage().getText().startsWith(TravelBot.HELLO_BUTTON)) {
            return;
        }

        Long chatId = update.getMessage().getChatId();
        Optional<City> city = cityRepository.getByName(update.getMessage().getText());
        String text;
        if(city.isPresent()){
            List<CityInfo> cityInfos = cityInfoRepository.getCityInfoByCity(city.get());

            text = cityInfos.get(0).getInfo();
        } else {
            text = "Данного города нет в нашей базе :(";
        }

        travelBot.sendTextMessage(chatId, text);

    }
}
