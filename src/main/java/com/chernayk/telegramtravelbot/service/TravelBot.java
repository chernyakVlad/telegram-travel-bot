package com.chernayk.telegramtravelbot.service;

import com.chernayk.telegramtravelbot.handler.TelegramMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class TravelBot extends TelegramLongPollingBot {

    public static final String HELLO_BUTTON = "Привет";
    public static final String START_COMMAND = "/start";
    public static final String HELP_BUTTON = "Помощь";

    @Value("${bot.travel.username}")
    String botUsername;
    @Value("${bot.travel.token}")
    String botToken;

    private final List<TelegramMessageHandler> telegramMessageHandlers;

    @Autowired
    public TravelBot(@Lazy List<TelegramMessageHandler> telegramMessageHandlers) {
        this.telegramMessageHandlers = telegramMessageHandlers;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        telegramMessageHandlers.forEach(
                handler -> handler.handle(update)
        );
    }

    public synchronized void sendTextMessage(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(getCustomReplyKeyboardMarkup());

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private ReplyKeyboardMarkup getCustomReplyKeyboardMarkup() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton(HELLO_BUTTON));
        keyboardFirstRow.add(new KeyboardButton(HELP_BUTTON));

        keyboard.add(keyboardFirstRow);

        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

    @Override
    public String getBotUsername() {
        return this.botUsername;
    }

    @Override
    public String getBotToken() {
        return this.botToken;
    }
}
