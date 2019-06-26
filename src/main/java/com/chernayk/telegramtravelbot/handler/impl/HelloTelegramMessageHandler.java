package com.chernayk.telegramtravelbot.handler.impl;

import com.chernayk.telegramtravelbot.handler.TelegramMessageHandler;
import com.chernayk.telegramtravelbot.service.TravelBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class HelloTelegramMessageHandler implements TelegramMessageHandler {

    private TravelBot travelBot;

    @Autowired
    public HelloTelegramMessageHandler(TravelBot travelBot) {
        this.travelBot = travelBot;
    }

    @Override
    public void handle(Update update) {
        if(!update.getMessage().getText().startsWith(TravelBot.HELLO_BUTTON)) {
            return;
        }

        Long chatId = update.getMessage().getChatId();

        String text = "Привет," + update.getMessage().getFrom().getFirstName() + " ! Я туристический бот.";

        travelBot.sendTextMessage(chatId, text);
    }
}
