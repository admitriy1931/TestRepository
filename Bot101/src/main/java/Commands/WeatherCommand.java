package commands;

import org.json.JSONObject;

public class WeatherCommand implements BotCommand {
    @Override
    public ParserOutput returnAnswer(String input) {
        String[] split;
        if (input.indexOf(' ') == -1)
            return new ParserOutput("Пожалуйста, введите комманду в виде комманду в правильном варианте");
        split = input.split(" ");
        if (split.length != 2)
            return new ParserOutput("В комманду передано неправильное количество аргументов");
        return printAboutWeather(split[1]);
    }

    public static ParserOutput printAboutWeather(String city) {

        String content = api.WeatherAPI.getContent(city);
        if (content.equals("Нет такого города"))
            return new ParserOutput(content);
        var result = jsonParser(content);
        return result.FormParserOutput();
    }

    public static JsonParserResult jsonParser(String inputResult) {
        var jsonObj = new JSONObject(inputResult);
        var temp = ("" + jsonObj.getJSONObject("main").getDouble("temp"));
        var tempMax = ("" + jsonObj.getJSONObject("main").getDouble("temp_max"));
        var tempMin = ("" + jsonObj.getJSONObject("main").getDouble("temp_min"));
        var pressure = ("" + jsonObj.getJSONObject("main").getDouble("pressure"));
        var clouds = ("" + jsonObj.getJSONObject("clouds").getDouble("all"));
        var icon = ("" + jsonObj.getJSONArray("weather").getJSONObject(0).getString("icon"));
        return new JsonParserResult(temp, pressure, clouds, icon);
    }
}
