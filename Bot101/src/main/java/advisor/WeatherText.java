package advisor;

import bot.Bot.Results;

public class WeatherText implements Recommendation {
    public String formOfRecommendation(long chatId) {
        String icon = Results.UsersInformation.get(chatId).ICON;
        String parseWeather = Results.UsersInformation.get(chatId).TEXT;
        String[] disparseWeather = parseWeather.split("\n");

        var temperatureAnalysis = WeatherAnalyzer.makeTemperatureAnalysis(disparseWeather[1]);
        var cloudsAnalysis = WeatherAnalyzer.makeCloudsAnalysis(disparseWeather[5]);
        var typeOfWeatherAnalysis = WeatherAnalyzer.makeIconAnalysis(icon);

        return String.format("За окном сегодня %s, на улице %s облачность, снаружи %s",
                temperatureAnalysis, cloudsAnalysis, typeOfWeatherAnalysis);
    }
}