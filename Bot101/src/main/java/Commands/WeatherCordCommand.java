package commands;

import org.json.JSONObject;

public class WeatherCordCommand implements BotCommand {
    @Override
    public ParserOutput returnAnswer(String input) {
        return printAboutWeather(input.split(" ")[1],
                input.split(" ")[2]);
    }

    private static ParserOutput printAboutWeather(String lat, String lon) {
        var result = jsonParser(api.WeatherAPI.getContent(lat, lon));
        return result.FormParserOutput();
    }

    public ParserOutput returnAnswerToLocation(String lat, String lon) {
        return printAboutWeather(lat, lon);
    }

    public static JsonParserResult jsonParser(String inputResult) {
        var jsonObj = new JSONObject(inputResult);
        var temp = ("" + jsonObj.getJSONObject("current").getDouble("temp"));
        var pressure = ("" + jsonObj.getJSONObject("current").getDouble("pressure"));
        var clouds = ("" + jsonObj.getJSONObject("current").getDouble("clouds"));
        var icon = ("" + jsonObj.getJSONObject("current").getJSONArray("weather").getJSONObject(0).getString("icon"));
        return new JsonParserResult(temp, pressure, clouds, icon);
    }
}
