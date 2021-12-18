package bot;

import commands.WeatherCordCommand;
import commands.BotCommand;
import commands.WeatherIndCommand;
import commands.WeatherIdCommand;
import commands.WeatherCommand;

import java.util.HashMap;

public class CommandTable {
    private static final HashMap<String, BotCommand> Table = constructHashMap();

    public static HashMap<String, BotCommand> getTable() {
        return Table;
    }

    private static HashMap<String, BotCommand> constructHashMap() {
        var commandDic = new HashMap<String, BotCommand>();
        commandDic.put("/weather", new WeatherCommand());
        commandDic.put("/weatherCord", new WeatherCordCommand());
        commandDic.put("/weatherId", new WeatherIdCommand());
        commandDic.put("/weatherInd", new WeatherIndCommand());
        return commandDic;
    }

    public static ResItem getItem(HashMap<String, BotCommand> commandTable,
                                  String messageText) {
        var result = (BotCommand) commandTable.get(messageText.split(" ")[0]);
        var resultOutput = result.returnAnswer(messageText);

        var resultAnswer = resultOutput.stringOutput;
        var splitAnswer = resultAnswer.split(System.lineSeparator());
        var icon = splitAnswer[splitAnswer.length - 5];
        var isFindIcon = icon.length() == 3;
        String messageTextResult = String.join(System.lineSeparator(), splitAnswer);
        return new ResItem(isFindIcon, messageTextResult, icon, resultOutput.recommendation, resultOutput.parserResult);
    }
}
