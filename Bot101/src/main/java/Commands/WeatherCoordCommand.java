package Commands;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class WeatherCoordCommand implements BotCommand
{
    @Override
    public String returnAnswer(String input) {
        return printAboutWeather(input.split("_")[1], input.split("_")[2]);
    }
    private static String printAboutWeather(String lat, String lon)
    {
        var result = JSONAnalyzer(API.WeatherAPI.GetContent(lat, lon));
        StringBuilder output = new StringBuilder();
        for(String el : result)
        {
            output.append(el).append(System.lineSeparator());
        }
        System.out.println(output);
        return output.toString();
    }
    private static List<String> JSONAnalyzer(String inputResult)
    {
        var jsonObj = new JSONObject(inputResult);
        var temp = ("" + jsonObj.getJSONObject("current").getDouble("temp"));
        //var tempMax = ("" + jsonObj.getJSONObject("current").getDouble("temp_max"));
        //var tempMin = ("" + jsonObj.getJSONObject("current").getDouble("temp_min"));
        var pressure = ("" + jsonObj.getJSONObject("current").getDouble("pressure"));
        var clouds = ("" + jsonObj.getJSONObject("current").getDouble("clouds"));
        return Arrays.asList("temp: ", temp,
                "pressure: ", pressure, "clouds: ",clouds);
    }
}
