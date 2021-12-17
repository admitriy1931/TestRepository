package advisor;

import bot.Bot;

public class ClothText implements Recommendation {
    @Override
    public String formOfRecommendation() {



        String icon = Bot.Results.ICON;
        var previousAnswerData = Bot.Results.PARSER_RESULT;

        var temp = Double.parseDouble(previousAnswerData.temp);
        var windy = Double.parseDouble(previousAnswerData.wind);
        var description = previousAnswerData.main;
        var chooser = new ClothChooser(temp, icon, windy, description);

        return "Сегодня в Ваш гардероб должны войти " + chooser.body + ", "
                + chooser.feets + ", " + chooser.head + ", " + chooser.legs;
    }
}
