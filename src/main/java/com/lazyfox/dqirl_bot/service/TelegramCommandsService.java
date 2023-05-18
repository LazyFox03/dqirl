package com.lazyfox.dqirl_bot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

public interface TelegramCommandsService {

    List<BotCommand> botCommandListInit();

    SendMessage encryptMessage(long chatId);

    SendMessage decipherMessage(long chatId);

//    MyUser registerUser(MyUser user);
//    boolean checkRegisterUserByChatId(long chatId);
//    MyUser getUserByChatId(long id);
//    SendMessage commandStart(MyUser user);
//    SendMessage commandProfile(MyUser user);
//    SendMessage commandQuests(MyUser user);
//    EditMessageText getSocialQuests(MyUser user, int messageId);
//    EditMessageText getCreativityQuests(MyUser user, int messageId);
//    EditMessageText getProductivityQuests(MyUser user, int messageId);

}
