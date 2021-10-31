package Commands;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class WeatherCordCommand implements BotCommand {
    @Override
    public String returnAnswer(String input) {
        return printAboutWeather(input.split("_")[1],
                input.split("_")[2]);
    }

    private static String printAboutWeather(String lat, String lon) {
        var result = JSONAnalyzer(API.WeatherAPI.GetContent(lat, lon));
        StringBuilder output = new StringBuilder();
        for (String el : result) {
            output.append(el).append(System.lineSeparator());
        }
        return output.toString();
    }

    public String returnAnswerToLocation(String lat, String lon) {
        return printAboutWeather(lat, lon);
    }

    public static List<String> JSONAnalyzer(String inputResult) {
        System.out.print(inputResult);
        var jsonObj = new JSONObject(inputResult);
        var temp = ("" + jsonObj.getJSONObject("current").getDouble("temp"));
        var pressure = ("" + jsonObj.getJSONObject("current").getDouble("pressure"));
        var clouds = ("" + jsonObj.getJSONObject("current").getDouble("clouds"));
        var icon = ("" + jsonObj.getJSONObject("current").getJSONArray("weather").getJSONObject(0).getString("icon"));
        return Arrays.asList("temp: ", temp,
                "pressure: ", pressure, "clouds: ", clouds, "icon", icon);
    }
}
