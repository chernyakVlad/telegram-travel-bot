package com.chernayk.telegramtravelbot.handler.impl;

import com.chernayk.telegramtravelbot.handler.TelegramMessageHandler;
import com.chernayk.telegramtravelbot.service.TravelBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class CityTelegramMessageHandler implements TelegramMessageHandler {

    private TravelBot travelBot;

    @Autowired
    public CityTelegramMessageHandler(TravelBot travelBot) {
        this.travelBot = travelBot;
    }

    @Override
    public void handle(Update update) {
        if(update.getMessage().getText().startsWith(TravelBot.HELP_BUTTON)
            || update.getMessage().getText().startsWith(TravelBot.HELLO_BUTTON)) {
            return;
        }

        Long chatId = update.getMessage().getChatId();

        String text = "К сожалению такого города нет в нашей базе. Вы можете добавить информацию о данном городе с помощью нашего API.";

        travelBot.sendTextMessage(chatId, text);
    }
}
