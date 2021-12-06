package commands;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class WeatherCommand implements BotCommand {
    @Override
    public ParserOutput returnAnswer(String input) {
        ArrayList<String> split;
        if (input.indexOf(' ') == -1)
            return new ParserOutput("Пожалуйста, введите комманду в виде комманду в правильном варианте");
        split = new ArrayList<>(Arrays.asList(input.split(" ")));
        split.remove(0);
        return printAboutWeather(String.join(" ", split));
    }

    public static ParserOutput printAboutWeather(String city) {

        String content = api.WeatherAPI.getContent(city);
        if (content.equals("Нет такого города"))
            return new ParserOutput(content);
        var result = JSONParser(content);
        return result.FormParserOutput();
    }

    public static JsonParserResult JSONParser(String inputResult) {
        var jsonObj = new JSONObject(inputResult);
        var temp = ("" + jsonObj.getJSONObject("main").getDouble("temp"));
        var tempMax = ("" + jsonObj.getJSONObject("main").getDouble("temp_max"));
        var tempMin = ("" + jsonObj.getJSONObject("main").getDouble("temp_min"));
        var pressure = ("" + jsonObj.getJSONObject("main").getDouble("pressure"));
        var clouds = ("" + jsonObj.getJSONObject("clouds").getDouble("all"));
        var main = ("" + jsonObj.getJSONArray("weather")
                .getJSONObject(0).getString("description"));
        var icon = ("" + jsonObj.getJSONArray("weather")
                .getJSONObject(0).getString("icon"));
        var wind = ("" + jsonObj.getJSONObject("wind").getDouble("speed"));
        return new JsonParserResult(temp, pressure, clouds, main, icon, wind);
    }
}
