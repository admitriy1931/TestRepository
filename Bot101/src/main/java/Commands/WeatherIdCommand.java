package Commands;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class WeatherIdCommand implements BotCommand {
    @Override
    public String returnAnswer(String input) {
        return printAboutWeather(input.split("_")[1]);
    }

    private static String printAboutWeather(String id) {
        var result = JSONParser(API.WeatherAPI.GetContentId(id));
        StringBuilder output = new StringBuilder();
        for (String el : result) {
            output.append(el).append(System.lineSeparator());
        }
        return output.toString();
    }

    public static List<String> JSONParser(String inputResult) {
        System.out.println(inputResult);
        var jsonObj = new JSONObject(inputResult);
        var temp = ("" + jsonObj.getJSONObject("main").getDouble("temp"));
        //var tempMax = ("" + jsonObj.getJSONObject("main").getDouble("temp_max"));
        //var tempMin = ("" + jsonObj.getJSONObject("main").getDouble("temp_min"));
        var pressure = ("" + jsonObj.getJSONObject("main").getDouble("pressure"));
        var clouds = ("" + jsonObj.getJSONObject("clouds").getDouble("all"));
        var icon = ("" + jsonObj.getJSONArray("weather").getJSONObject(0).getString("icon"));
        return Arrays.asList("temp: ", temp,
                "pressure: ", pressure, "clouds: ", clouds, "icon", icon);
    }
}
