package commands;

import org.json.JSONObject;

public class WeatherCommand implements BotCommand {
    @Override
    public String returnAnswer(String input) {
        String[] split;
        if (input.indexOf(' ') == -1)
            return "Пожалуйста, введите комманду в виде комманду в правильном варианте";
        split = input.split(" ");
        if (split.length != 2)
            return "В комманду передано неправильное количество аргументов";
        return printAboutWeather(split[1]);
    }

    public static String printAboutWeather(String city) {

        String content = api.WeatherAPI.GetContent(city);
        if (content.equals("Нет такого города"))
            return content;
        var result = JSONParser(content);
        return result.FormOutput();
    }

    public static JsonParserResult JSONParser(String inputResult) {
        var jsonObj = new JSONObject(inputResult);
        var temp = ("" + jsonObj.getJSONObject("main").getDouble("temp"));
        var tempMax = ("" + jsonObj.getJSONObject("main").getDouble("temp_max"));
        var tempMin = ("" + jsonObj.getJSONObject("main").getDouble("temp_min"));
        var pressure = ("" + jsonObj.getJSONObject("main").getDouble("pressure"));
        var clouds = ("" + jsonObj.getJSONObject("clouds").getDouble("all"));
        var icon = ("" + jsonObj.getJSONArray("weather").getJSONObject(0).getString("icon"));
        return new JsonParserResult(temp, pressure, clouds, icon);
        /*
        return Arrays.asList("temp: ", temp, "tempMax: ", tempMax,
                "tempMin: ", tempMin, "pressure: ", pressure, "clouds: ", clouds, "icon", icon);
         */
    }
}
