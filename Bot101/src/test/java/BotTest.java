import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;

import static org.junit.jupiter.api.Assertions.*;

class BotTest{

    @Test
    void main() {

    }

    @Test
    void onUpdateReceived() {


    }

    @Test
    void getBotUsername() {
        assertEquals("att1000_bot", new String("att1000_bot"));
    }

    @Test
    void getBotToken() {

    }

    @Test
    void onClosing() {
    }
}