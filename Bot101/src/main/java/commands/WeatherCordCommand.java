package commands;

import org.json.JSONObject;

public class WeatherCordCommand implements BotCommand {
    @Override
    public String returnAnswer(String input) {
        return printAboutWeather(input.split(" ")[1],
                input.split(" ")[2]);
    }

    private static String printAboutWeather(String lat, String lon) {
        var result = JSONParser(api.WeatherAPI.GetContent(lat, lon));
        return result.FormOutput();
    }

    public String returnAnswerToLocation(String lat, String lon) {
        return printAboutWeather(lat, lon);
    }

    public static JsonParserResult JSONParser(String inputResult) {
        var jsonObj = new JSONObject(inputResult);
        var temp = ("" + jsonObj.getJSONObject("current").getDouble("temp"));
        var pressure = ("" + jsonObj.getJSONObject("current").getDouble("pressure"));
        var clouds = ("" + jsonObj.getJSONObject("current").getDouble("clouds"));
        var icon = ("" + jsonObj.getJSONObject("current").getJSONArray("weather").getJSONObject(0).getString("icon"));
        return new JsonParserResult(temp, pressure, clouds, icon);
    }
}
