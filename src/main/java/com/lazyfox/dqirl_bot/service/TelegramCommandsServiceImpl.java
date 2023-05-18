package com.lazyfox.dqirl_bot.service;


import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelegramCommandsServiceImpl implements TelegramCommandsService {

    @Override
    public List<BotCommand> botCommandListInit() {
        List<BotCommand> commandList = new ArrayList<>();
        commandList.add(new BotCommand("/start", "hello bot"));
        commandList.add(new BotCommand("/encrypt", "зашифровка"));
        commandList.add(new BotCommand("/decrypt", "расшифровка"));
        return commandList;
    }

    @Override
    public SendMessage encryptMessage(long chatId) {
        return (new SendMessage(String.valueOf(chatId), "чтоб зашифровать сообщение нужно написать боту \"encrypt: \" и после двоеточия поставить пробел и написать сообщение которое нужно зашифровать"));
    }

    @Override
    public SendMessage decipherMessage(long chatId) {
        return (new SendMessage(String.valueOf(chatId), "чтоб расшифровать сообщение нужно написать боту \"decrypt: \" и после двоеточия поставить пробел и написать сообщение которое нужно расшифровать"));
    }

}
