package Commands;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class WeatherIndCommand implements BotCommand {
    @Override
    public String returnAnswer(String input) {
        return printAboutWeather(input.split("_")[1], input.split("_")[2]);
    }

    private static String printAboutWeather(String ind, String countryCode) {
        var result = JSONParser(API.WeatherAPI.GetContentInd(ind, countryCode));
        StringBuilder output = new StringBuilder();
        for (String el : result) {
            output.append(el).append(System.lineSeparator());
        }
        System.out.println(output);
        return output.toString();
    }

    private static List<String> JSONParser(String inputResult) {
        System.out.println(inputResult);
        var jsonObj = new JSONObject(inputResult);
        var temp = ("" + jsonObj.getJSONObject("main").getDouble("temp"));
        var tempMax = ("" + jsonObj.getJSONObject("main").getDouble("temp_max"));
        var tempMin = ("" + jsonObj.getJSONObject("main").getDouble("temp_min"));
        var pressure = ("" + jsonObj.getJSONObject("main").getDouble("pressure"));
        var clouds = ("" + jsonObj.getJSONObject("clouds").getDouble("all"));
        return Arrays.asList("temp: ", temp, "tempMax: ", tempMax,
                "tempMin: ", tempMin, "pressure: ", pressure, "clouds: ", clouds);
    }
}
