/*
package Bot;

import Bot.Bot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;


import java.util.Map;

public class MentalCalculationApplication {
    private static final Map<String, String> getenv = System.getenv();

    public static void main(String[] args) {

        ApiContextInitializer.init();
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi();
            botsApi.registerBot(new Bot(getenv.get("BOT_NAME"), getenv.get("BOT_TOKEN")));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
*/