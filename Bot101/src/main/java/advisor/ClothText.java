package advisor;

import bot.Bot;

public class ClothText implements Recommendation {
    @Override
    public String formOfRecommendation() {

        String icon = Bot.Results.Icon;
        String parseWeather = Bot.Results.tempPressClouds;
        String[] DisparseWeather = parseWeather.split("\n");
        var temp = DisparseWeather[1];
        var windy = DisparseWeather[9];
        var description = DisparseWeather[11];
        var Chooser = new ClothChooser(temp, icon, windy, description);

        return "Сегодня в Ваш гардероб должны войти " + Chooser.Body + ", "
                + Chooser.Feets + ", " + Chooser.Head + ", " + Chooser.Legs;
    }
}
