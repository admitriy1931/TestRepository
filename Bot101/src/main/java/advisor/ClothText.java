package advisor;

import bot.Bot;

public class ClothText implements Recommendation {
    @Override
    public String formOfRecommendation() {

        String icon = Bot.Results.Icon;
        String parseWeather = Bot.Results.tempPressClouds;
        String[] disparseWeather = parseWeather.split("\n");
        var temp = Double.parseDouble(disparseWeather[1]);
        var windy = Double.parseDouble(disparseWeather[9]);
        var description = disparseWeather[11];
        var chooser = new ClothChooser(temp, icon, windy, description);

        return "Сегодня в Ваш гардероб должны войти " + chooser.body + ", "
                + chooser.feets + ", " + chooser.head + ", " + chooser.legs;
    }
}
