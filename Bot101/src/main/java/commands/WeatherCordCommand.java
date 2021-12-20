package commands;

import bot.Bot;
import org.json.JSONObject;

public class WeatherCordCommand implements BotCommand {
    @Override
    public ParserOutput returnAnswer(String input) {
        return printAboutWeather(input.split(" ")[1],
                input.split(" ")[2]);
    }

    private static ParserOutput printAboutWeather(String lat, String lon) {
        var result = JSONParser(api.WeatherAPI.getContent(lat, lon));
        return result.FormParserOutput();
    }

    public ParserOutput returnAnswerToLocation(String lat, String lon) {
        //Bot.Results.UsersInformation.get(chatId).LAT = lat;
        //Bot.Results.UsersInformation.get(chatId).LON = lon;
        return printAboutWeather(lat, lon);
    }

    public static JsonParserResult JSONParser(String inputResult) {
        var jsonObj = new JSONObject(inputResult);
        var temp = ("" + jsonObj.getJSONObject("current").getDouble("temp"));
        var pressure = ("" + jsonObj.getJSONObject("current").getDouble("pressure"));
        var clouds = ("" + jsonObj.getJSONObject("current").getDouble("clouds"));
        var main = ("" + jsonObj.getJSONObject("current").getJSONArray("weather").getJSONObject(0).getString("description"));
        var icon = ("" + jsonObj.getJSONObject("current").getJSONArray("weather").getJSONObject(0).getString("icon"));
        var wind = ("" + jsonObj.getJSONObject("current").getDouble("wind_speed"));
        return new JsonParserResult(temp, pressure, clouds, main, icon, wind);
    }
}
