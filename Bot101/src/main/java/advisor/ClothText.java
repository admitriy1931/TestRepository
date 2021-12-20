package advisor;

import bot.Bot;

public class ClothText implements Recommendation {
    @Override
    public String formOfRecommendation(long chatId) {


        String icon = Bot.Results.UsersInformation.get(chatId).ICON;
        var previousAnswerData = Bot.Results.UsersInformation.get(chatId).PARSER_RESULT;

        var temp = Double.parseDouble(previousAnswerData.temp);
        var windy = Double.parseDouble(previousAnswerData.wind);
        var description = previousAnswerData.main;
        var chooser = new ClothChooser(temp, icon, windy, description);

        return "Сегодня в Ваш гардероб должны войти " + chooser.body + ", "
                + chooser.feets + ", " + chooser.head + ", " + chooser.legs;
    }
}
