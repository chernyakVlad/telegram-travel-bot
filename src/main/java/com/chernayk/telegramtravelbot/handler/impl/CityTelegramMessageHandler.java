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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CityTelegramMessageHandler implements TelegramMessageHandler {

    public static String CITY_NOT_FOUND_TEXT = "Данного города нет в моей базе :(";
    public static String CITY_INFOS_NOT_FOUND_TEXT = "К сожалению информации о данном городе нет в моей базе :(";
    public static String CITY_TEXT = "Не забудь посетить \ud83d\udd2d:";
    public static String DIAMOND_EMOJI = "\ud83d\udd39";

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
            || update.getMessage().getText().startsWith(TravelBot.HELLO_BUTTON)
            || update.getMessage().getText().startsWith(TravelBot.START_COMMAND)) {
            return;
        }

        Long chatId = update.getMessage().getChatId();

        Optional<City> city = cityRepository.getByName(update.getMessage().getText());

        if(city.isPresent()){
            List<CityInfo> cityInfos = cityInfoRepository.getAllByCityId(city.get().getId());
            if(cityInfos == null || cityInfos.size() == 0) {
                travelBot.sendTextMessage(chatId, CITY_INFOS_NOT_FOUND_TEXT);
            }
            else {
                travelBot.sendTextMessage(chatId, createMessageText(cityInfos));
            }
        } else {
            travelBot.sendTextMessage(chatId, CITY_NOT_FOUND_TEXT);
        }
    }

    private String createMessageText(List<CityInfo> cityInfos) {

        String infoList = cityInfos.stream()
                .filter(Objects::nonNull)
                .map(CityInfo::getInfo)
                .map(info -> DIAMOND_EMOJI + info)
                .collect(Collectors.joining("\n"));

        return CITY_TEXT + "\n" + infoList;
    }
}
