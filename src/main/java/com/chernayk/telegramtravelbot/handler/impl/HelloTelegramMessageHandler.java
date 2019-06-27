package com.chernayk.telegramtravelbot.handler.impl;

import com.chernayk.telegramtravelbot.handler.TelegramMessageHandler;
import com.chernayk.telegramtravelbot.service.TravelBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        User user = update.getMessage().getFrom();

        String text = Stream.of("Привет,", user.getLastName(), user.getFirstName())
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" "));

        travelBot.sendTextMessage(chatId, text);
    }
}
