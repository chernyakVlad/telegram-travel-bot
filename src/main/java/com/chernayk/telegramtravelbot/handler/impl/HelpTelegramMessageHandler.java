package com.chernayk.telegramtravelbot.handler.impl;

import com.chernayk.telegramtravelbot.handler.TelegramMessageHandler;
import com.chernayk.telegramtravelbot.service.TravelBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class HelpTelegramMessageHandler implements TelegramMessageHandler {

    @Autowired
    TravelBot travelBot;

    @Override
    public void handle(Update update) {
        if(!update.getMessage().getText().startsWith(TravelBot.HELP_BUTTON)) {
            return;
        }

        Long chatId = update.getMessage().getChatId();

        String text = "Я туристический бот. Я выдаю справочную информацию о городах. Для начала введи название города.";

        travelBot.sendTextMessage(chatId, text);
    }
}
