import advisor.ClothChooser;
import commands.*;
import org.junit.jupiter.api.Test;
import bot.CommandTable;
import bot.Bot;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BotTest {
    public HashMap commandTable = CommandTable.getTable();
    public Constants testsConstants = new Constants();
   /*
    @Test
    void getRightSimpleAnswer() {
        var answer1 = "Привет, я робот! Я умею рассказать о себе по комманде /about, " +
                "а еще могу вернуть погоду, для этого введи /weather" +
                " пожалуйста, указывайте город английскими буквами" +
                "Ну а если тебе нужен быстрый результат, скинь свою геоокацию в чат со мной," +
                "я выведу погоду по твоему местоположению)";
        var answer2 = "Я - Бот для Telegram, написанный на java. " +
                "Комманда разработки: Агафонов Дмитрий, Сливный Артём";
        var answer3 = "Я не знаю, что тебе ответить, ты ввел неправильную комманду";
        var testBot = new Bot();
        assertEquals(new Bot().getAnswerToCommand("/help", new Message()).stringOutput, answer1);
        assertEquals(testBot.getAnswerToCommand("/about", new Message()).stringOutput, answer2);
        assertEquals(testBot.getAnswerToCommand("/abcdefg", new Message()).stringOutput, answer3);
    }
    */




    @Test
    void getRightWeatherAnswer() {
        var jsonWeather = "{\"coord\":{\"lon\":60.6125,\"lat\":56.8575},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"base\":\"stations\",\"main\":{\"temp\":1.77,\"feels_like\":-3.2,\"temp_min\":1.77,\"temp_max\":1.77,\"pressure\":1013,\"humidity\":100},\"visibility\":10000,\"wind\":{\"speed\":6,\"deg\":300},\"rain\":{\"1h\":0.11},\"clouds\":{\"all\":90},\"dt\":1635620614,\"sys\":{\"type\":1,\"id\":8985,\"country\":\"RU\",\"sunrise\":1635649497,\"sunset\":1635682634},\"timezone\":18000,\"id\":1486209,\"name\":\"Ekaterinburg\",\"cod\":200}";
        var parserResult = new JsonParserResult("1.77", "1013.0", "90.0", "light rain", "10n", "6.0").FormParserOutput();
        assertEquals(parserResult.stringOutput,
                WeatherCommand.JSONParser(jsonWeather).FormParserOutput().stringOutput);
        assertEquals(parserResult.recommendation,
                WeatherCommand.JSONParser(jsonWeather).FormParserOutput().recommendation);
        /*
        var cloth = new ClothChooser(1.77, "10", 6.0, "light rain");

        assertEquals(cloth.body, "Куртка");
        assertEquals(cloth.legs, "Джинсы");
        assertEquals(cloth.head, "Шапка");
        assertEquals(cloth.feets, "Ботинки");
         */

    }

    @Test
    void getRightWeatherCordAnswer() {
        var jsonWeather = testsConstants.CordinateRequestAnswer;
        var parserResult = new JsonParserResult("-9.19", "1017.0", "87.0","overcast clouds", "04n", "2.53").FormParserOutput();
        assertEquals(parserResult.stringOutput,
                WeatherCordCommand.JSONParser(jsonWeather).FormParserOutput().stringOutput);
        //assertEquals(parserResult.recommendation,
                //WeatherCordCommand.JSONParser(jsonWeather).FormParserOutput().recommendation);

        /*
        var cloth = new ClothChooser(-9.19, "04", 2.53, "overcast clouds");

        assertEquals(cloth.body, "Куртка");
        assertEquals(cloth.legs, "Джинсы");
        assertEquals(cloth.head, "Шапка");
        assertEquals(cloth.feets, "Ботинки");
         */
    }

    @Test
    void getRightWeatherIdAnswer() {
        var jsonWeather = "{\"coord\":{\"lon\":60.6125,\"lat\":56.8575},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\"," +
                "\"icon\":\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":277.92,\"feels_like\":274.2,\"temp_min\":277.92,\"temp_max\":277.92,\"pressure" +
                "\":1017,\"humidity\":81},\"visibility\":10000,\"wind\":{\"speed\":5,\"deg\":250},\"clouds\":{\"all\":75},\"dt\":1635678622,\"sys\":{\"type" +
                "\":1,\"id\":8985,\"country\":\"RU\",\"sunrise\":1635649497,\"sunset\":1635682634},\"timezone\":18000,\"id\":1486209,\"name\":\"Ekaterinburg" +
                "\",\"cod\":200}";
        var parserResult = new JsonParserResult("277.92", "1017.0", "75.0","broken clouds", "04d",  "5.0").FormParserOutput();
        assertEquals(parserResult.stringOutput,
                WeatherIdCommand.JSONParser(jsonWeather).FormParserOutput().stringOutput);
        assertEquals(parserResult.recommendation,
                WeatherIdCommand.JSONParser(jsonWeather).FormParserOutput().recommendation);

        /*
        var cloth = new ClothChooser(277.92, "04", 5.0, "broken clouds");

        assertEquals(cloth.body, "Лонгслив");
        assertEquals(cloth.legs, "Брюки-карго");
        assertEquals(cloth.head, "Бейсболка");
        assertEquals(cloth.feets, "Кроссовки");
         */
    }

    @Test
    void getRightWeatherIndAnswer() {
        var jsonWeather = "{\"coord\":{\"lon\":60.52,\"lat\":56.6892},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\"" +
                ":\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":4.62,\"feels_like\":1.39,\"temp_min\":4.62,\"temp_max\":4.62,\"pressure\":1017,\"" +
                "\":87},\"visibility\":10000,\"wind\":{\"speed\":4,\"deg\":240},\"clouds\":{\"all\":75},\"dt\":1635680247,\"sys\":{\"type\":1,\"id\":8985," +
                "\"country\":\"RU\",\"sunrise\":1635649485,\"sunset\":1635682691},\"timezone\":18000,\"id\":0,\"name\":\"Горный Щит\",\"cod\":200}";
        var parserResult = new JsonParserResult("4.62", "1017.0", "75.0","Clouds", "04d", "4.0").FormParserOutput();
        assertEquals(parserResult.stringOutput,
                WeatherIndCommand.JSONParser(jsonWeather).FormParserOutput().stringOutput);
        assertEquals(parserResult.recommendation,
                WeatherIndCommand.JSONParser(jsonWeather).FormParserOutput().recommendation);

        /*
        var cloth = new ClothChooser(4.62, "04", 4.0, "Clouds");
        assertEquals(cloth.body, "Куртка");
        assertEquals(cloth.legs, "Джинсы");
        assertEquals(cloth.head, "Шапка");
        assertEquals(cloth.feets, "Ботинки");
         */
    }

    @Test
    void getClothForSoftSnowWeather(){
        var cloth = new ClothChooser(-9.0, "04", 4.0, "snow");
        assertEquals(cloth.body, "Пуховик");
        assertEquals(cloth.legs, "Утепленные джинсы");
        assertEquals(cloth.head, "Шапка");
        assertEquals(cloth.feets, "Теплые ботинки");
    }

    @Test
    void getClothForStormSnowWeather(){
        var cloth = new ClothChooser(-4.62, "04", 19.0, "snow");
        assertEquals(cloth.body, "Парка");
        assertEquals(cloth.legs, "Утепленные джинсы");
        assertEquals(cloth.head, "Шапка");
        assertEquals(cloth.feets, "Зимние сапоги");
    }

    @Test
    void getClothForWindlessRainyColdWeather(){
        var cloth = new ClothChooser(4.62, "04", 0.1, "Lite rain");
        assertEquals(cloth.body, "Куртка");
        assertEquals(cloth.legs, "Джинсы");
        assertEquals(cloth.head, "Шапка");
        assertEquals(cloth.feets, "Сапоги");
    }

    @Test
    void getClothForWindlessRainyWarmWeather(){
        var cloth = new ClothChooser(17.62, "04", 0.0, "Heavy rain");
        assertEquals(cloth.body, "Худи");
        assertEquals(cloth.legs, "Джинсы");
        assertEquals(cloth.head, "Бейсболка");
        assertEquals(cloth.feets, "Кроссовки");
    }

    @Test
    void getClothForWindlessSoftWarmWeather(){
        var cloth = new ClothChooser(14.62, "04", 0.3, "clouds");
        assertEquals(cloth.body, "Поло");
        assertEquals(cloth.legs, "Шорты");
        assertEquals(cloth.head, "Легкая бейсболка");
        assertEquals(cloth.feets, "Мокасины");
    }

    @Test
    void getClothForWindlessSoftColdWeather(){
        var cloth = new ClothChooser(4.62, "04", 0.1, "clouds");
        assertEquals(cloth.body, "Куртка");
        assertEquals(cloth.legs, "Джинсы");
        assertEquals(cloth.head, "Шапка");
        assertEquals(cloth.feets, "Походные ботинки");
    }

    @Test
    void getClothForWindyRainyWarmWeather(){
        var cloth = new ClothChooser(14.62, "04", 5.0, "rainy");
        assertEquals(cloth.body, "Ветровка");
        assertEquals(cloth.legs, "Джинсы");
        assertEquals(cloth.head, "Бейсболка");
        assertEquals(cloth.feets, "Кеды");
    }

    @Test
    void getClothForWindySoftWarmWeather(){
        var cloth = new ClothChooser(14.67, "04", 3.0, "clouds");
        assertEquals(cloth.body, "Лонгслив");
        assertEquals(cloth.legs, "Брюки-карго");
        assertEquals(cloth.head, "Бейсболка");
        assertEquals(cloth.feets, "Кроссовки");
    }
    @Test
    void getClothForWindyRainyColdWeather(){
        var cloth = new ClothChooser(4.62, "04", 4.0, "clouds");
        assertEquals(cloth.body, "Куртка");
        assertEquals(cloth.legs, "Джинсы");
        assertEquals(cloth.head, "Шапка");
        assertEquals(cloth.feets, "Ботинки");
    }

    @Test
    void getClothForWindySoftColdWeather(){
        var cloth = new ClothChooser(2.72, "04", 6.0, "clouds");
        assertEquals(cloth.body, "Куртка");
        assertEquals(cloth.legs, "Джинсы");
        assertEquals(cloth.head, "Шапка");
        assertEquals(cloth.feets, "Ботинки");
    }


    @Test
    void getBotUsername() {
        assertEquals(new Bot().getBotUsername(), "DownloadAssistantBot");
    }

//    @Test
//    void getBotToken() {
//        var testBot = new Bot();
//        assertEquals(testBot.getBotToken(), "2045710377:AAExPCy4WnncxlwKW8io3aY6lartX3tcTC4");
//    }
}