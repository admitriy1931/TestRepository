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

import java.security.MessageDigest;

import static org.junit.jupiter.api.Assertions.*;

class BotTest{
/*
    @Test
    void main() {

    }

    @Test
    void onUpdateReceived() {


    }
*/
    @Test
    void getRightAnswer(){
        var answ1 = "Привет, я робот! Я умею рассказать о себе по комманде /about, " +
                "а еще могу вернуть погоду, для этого введи /weather" +
                " пожалуйста, указывайте город английскими буквами";
        var answ2 = "Я - Бот для Telegram, написанный на java. " +
                "Комманда разработки: Агафонов Дмитрий, Сливный Артём";
        var answ3 ="temp: " + System.lineSeparator() +
                "1.87" + System.lineSeparator() +
                "tempMax: " + System.lineSeparator() +
                "2.91" + System.lineSeparator() +
                "tempMin: " + System.lineSeparator() +
                "-0.4" + System.lineSeparator() +
                "pressure: " + System.lineSeparator() +
                "1011.0" + System.lineSeparator() +
                "clouds: " + System.lineSeparator() +
                "87.0";
        var answ4 ="Я не знаю, что тебе ответить, ты ввел неправильную комманду";;
//        assertEquals(new Bot().getAnswerToCommand("/help"), answ1);
//        assertEquals(new Bot().getAnswerToCommand("/about"),answ2);
//        //assertEquals(new Bot().getAnswerToCommand("/weather"),answ3);
//        assertEquals(new Bot().getAnswerToCommand("/kfjgh"),answ4);
    }
    @Test
    void getBotUsername() {
        assertEquals(new Bot().getBotUsername(), new String("att1000_bot"));
    }

    @Test
    void getBotToken() {
//        assertEquals(new Bot().getBotToken(), kkkkkkkkkkkkkkk);
        /*
        Мысль относительно этого теста: надо вычислить sha-256 от нашего токена и проверять его в сравнении с
        sha-256, поданным на вход
        */
    }

/*
    @Test
    void onClosing() {
    }
 */
}