package Bot;

import Commands.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
        //commandDic.put("/weatherLoc", new WeatherLocCommand());
        //commandDic.put("/weatherLoc", new WeatherLocCommand(lat, lon));

        return commandDic;
    }

    public static HashMap<String, String> getItem(HashMap commandTable,
                                                  String messageText) {
        var result = (BotCommand) commandTable.get(messageText.split("_")[0]);
        var resultAnswer = result.returnAnswer(messageText);
        var splitAnswer = resultAnswer.split(System.lineSeparator());
        var icon = splitAnswer[splitAnswer.length-1];
        var isFindIcon = icon.length() == 3;
        if (isFindIcon)
            splitAnswer = Arrays.copyOf(splitAnswer, splitAnswer.length-2);
        String messageTextResult = String.join(System.lineSeparator(), splitAnswer);
        var resDict = new HashMap<String, String>();
        resDict.put("result", messageTextResult);
        if (isFindIcon)
            resDict.put("icon", icon);
        return resDict;
    }
}