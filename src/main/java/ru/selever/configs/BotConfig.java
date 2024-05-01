package ru.selever.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.selever.bot.TgBot;
@Configuration
public class BotConfig {
    @Bean
    public TelegramBotsApi telegramBotsApi(TgBot tgBot) throws TelegramApiException {
        var api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(tgBot);
        return api;
    }
}
