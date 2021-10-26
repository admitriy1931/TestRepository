import org.junit.jupiter.api.Test;
import Bot.*;
import org.telegram.telegrambots.api.objects.Message;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BotTest{
    public HashMap commandTable = CommandTable.getTable();
    @Test
    void getRightSimpleAnswer() {
        var answer1 = "Привет, я робот! Я умею рассказать о себе по комманде /about, " +
                "а еще могу вернуть погоду, для этого введи /weather" +
                " пожалуйста, указывайте город английскими буквами";
        var answer2 = "Я - Бот для Telegram, написанный на java. " +
                "Комманда разработки: Агафонов Дмитрий, Сливный Артём";
        var answer3 = "Я не знаю, что тебе ответить, ты ввел неправильную комманду";
        var testBot = new Bot();
        assertEquals(new Bot().getAnswerToCommand("/help", new Message()), answer1);
        assertEquals(testBot.getAnswerToCommand("/about", new Message()), answer2);
        assertEquals(testBot.getAnswerToCommand("/kfjgh", new Message()), answer3);
    }
    @Test
    void getRightWeatherAnswer() {
        var weatherRes = CommandTable.getItem(commandTable, "/weather_moscow");
        var result = weatherRes.get("result");
        assertTrue(containsAll(result) && result.contains("tempMax: ") && result.contains("tempMin: "));
        assertTrue(containsIcon(weatherRes.get("icon")));
    }
    @Test
    void getRightWeatherCordAnswer() {
        var weatherCord = CommandTable.getItem(commandTable, "/weatherCord_15_17.0");
        var cordResult = weatherCord.get("result");
        assertTrue(containsAll(cordResult));
        assertTrue(containsIcon(weatherCord.get("icon")));
    }
    @Test
    void getRightWeatherIdAnswer() {
        var weatherId = CommandTable.getItem(commandTable, "/weather_moscow");
        var idResult = weatherId.get("result");
        assertTrue(containsAll(idResult) && idResult.contains("tempMax: ") && idResult.contains("tempMin: "));
        assertTrue(containsIcon(weatherId.get("icon")));
    }
    @Test
    void getRightWeatherIndAnswer() {
        var weatherInd = CommandTable.getItem(commandTable, "/weather_moscow");
        var indResult = weatherInd.get("result");
        assertTrue(containsAll(indResult) && indResult.contains("tempMax: ") && indResult.contains("tempMin: "));
        assertTrue(containsIcon(weatherInd.get("icon")));
    }

    private boolean containsIcon(String icon){
        return (icon.contains("n")|| icon.contains("d"))&&icon.length()==3;
    }
    private boolean containsAll(String answerToCommand) {
        return answerToCommand.contains("temp") && answerToCommand.contains( "pressure")&&
                answerToCommand.contains("clouds");
    }

    @Test
    void getBotUsername() {
        assertEquals(new Bot().getBotUsername(), new String("att1000_bot"));
    }

    @Test
    void getBotToken() {
        var testBot = new Bot();
       assertEquals(testBot.getBotToken(), "ыовраплыораплыврпалыврпалпалп");
    }
}