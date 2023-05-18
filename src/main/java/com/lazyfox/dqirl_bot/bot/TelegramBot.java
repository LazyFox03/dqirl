package com.lazyfox.dqirl_bot.bot;

import com.lazyfox.dqirl_bot.configuration.BotConfig;
import com.lazyfox.dqirl_bot.service.TelegramCommandsService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig config;
    private final TelegramCommandsService botCommands;

    public TelegramBot(BotConfig config, TelegramCommandsService botCommands) {
        this.config = config;
        this.botCommands = botCommands;
        try {
            this.execute(new SetMyCommands(botCommands.botCommandListInit(), new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Произошла ошибка: " + e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getBotToken();
    }


    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (messageText.split(" ")[0].equals("encrypt:")) {
                String[] enc = messageText.split(" ", 2);
                char[] chars = enc[1].toCharArray();
                StringBuilder stringBuilder = new StringBuilder();
                for (char a : chars) {
                    stringBuilder.append((char) (a + 5));
                }
                execute(new SendMessage(String.valueOf(chatId), "Ваше сообщение зашифрованно: " + stringBuilder));
            } else if (messageText.split(" ")[0].equals("decrypt:")) {
                String[] enc = messageText.split(" ", 2);
                char[] chars = enc[1].toCharArray();
                StringBuilder stringBuilder = new StringBuilder();
                for (char a : chars) {
                    stringBuilder.append((char) (a - 5));
                }
                execute(new SendMessage(String.valueOf(chatId), "Ваше сообщение расшифровано: " + stringBuilder));
            } else {
                switch (messageText) {
                    case "/start":
                        execute(new SendMessage(String.valueOf(chatId), "Start bot"));
                        break;
                    case "/encrypt":
                        execute(botCommands.encryptMessage(chatId));
                        break;
                    case "/decrypt":
                        execute(botCommands.decipherMessage(chatId));
                        break;
                    default:
                        execute(new SendMessage(String.valueOf(chatId), "нет такой команды"));
                }
            }
        }
    }
}
