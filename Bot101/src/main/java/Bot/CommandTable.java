package bot;

import commands.WeatherCordCommand;
import commands.BotCommand;
import commands.HelpCommand;
import commands.AboutCommand;
import commands.WeatherIndCommand;
import commands.WeatherIdCommand;
import commands.WeatherCommand;

import java.util.Arrays;
import java.util.HashMap;

public class CommandTable {
    private static final HashMap Table = constructHashMap();

    public static HashMap getTable() {
        return Table;
    }

    private static HashMap constructHashMap() {
        var commandDic = new HashMap<String, BotCommand>();

        commandDic.put("/help", new HelpCommand());
        commandDic.put("/about", new AboutCommand());
        commandDic.put("/weather", new WeatherCommand());
        commandDic.put("/weatherCord", new WeatherCordCommand());
        commandDic.put("/weatherId", new WeatherIdCommand());
        commandDic.put("/weatherInd", new WeatherIndCommand());
        return commandDic;
    }

    public static ResItem getItem(HashMap commandTable,
                                  String messageText) {
        var result = (BotCommand) commandTable.get(messageText.split(" ")[0]);
        var resultAnswer = result.returnAnswer(messageText);
        var splitAnswer = resultAnswer.split(System.lineSeparator());
        var icon = splitAnswer[splitAnswer.length - 1];
        var isFindIcon = icon.length() == 3;
        if (isFindIcon)
            splitAnswer = Arrays.copyOf(splitAnswer, splitAnswer.length - 2);
        String messageTextResult = String.join(System.lineSeparator(), splitAnswer);
        return new ResItem(isFindIcon, messageTextResult, icon);
    }
}
