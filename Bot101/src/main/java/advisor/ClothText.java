package advisor;

import bot.Bot;

public class ClothText implements Recommendation {
    @Override
    public String FormOfRecommendation() {

        String icon = Bot.Results.Icon;
        String parseWeather = Bot.Results.tempPressClouds;
        String[] DisparseWeather = parseWeather.split("\n");
        var temp = DisparseWeather[1];
        var windy = DisparseWeather[9];
        var Chooser = new ClothChooser(temp, icon, windy);

        return "Сегодня Вам лучше надеть " + Chooser.Body + ' '
                + Chooser.Feets + ' ' + Chooser.Head + ' ' + Chooser.Legs;
    }
}
