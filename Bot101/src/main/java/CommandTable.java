import Commands.*;

import java.util.HashMap;

public class CommandTable {
    private static Double lat;
    private static Double lon;
    public  CommandTable(){

    }
    public  CommandTable(Double lat, Double lon){
        this.lat = lat;
        this.lon = lon;

    }
    private static final HashMap Table = constructHashMap();

    public static HashMap getTable() {
        return Table;
    }

    private static HashMap constructHashMap() {
        var commandDic = new HashMap<String, BotCommand>();

        commandDic.put("/help", new HelpCommand());
        commandDic.put("/about", new AboutCommand());
        commandDic.put("/weather", new WeatherCommand());
        commandDic.put("/weatherCoord", new WeatherCoordCommand());
        commandDic.put("/weatherId", new WeatherIdCommand());
        commandDic.put("/weatherInd", new WeatherIndCommand());
        commandDic.put("/weatherLoc", new WeatherLocCommand(lat, lon));

        return commandDic;
    }

    public static String getItem(HashMap commandTable,
                                 String messageText) {
        var result = (BotCommand) commandTable.get(messageText.split("_")[0]);
        return result.returnAnswer(messageText);
    }

}

/*
    private static String printAboutWeather(String city)
    {
        System.out.println(WeatherAPI.GetContent(city));
        var result = JSONAnalyzer(WeatherAPI.GetContent(city));
        StringBuilder output = new StringBuilder();
        for(String el : result)
        {
            System.out.println(el);
            output.append(el).append(System.lineSeparator());
        }
        return output.toString();
    }
*/

    /*
        private static String PrintHelp()
        {
            return "Привет, я робот! Я умею рассказать о себе по комманде /about, " +
                    "а еще могу вернуть погоду, для этого введи /weather" +
                    " пожалуйста, указывайте город английскими буквами";
        }

        private static String PrintAbout()
        {
            return "Я - Бот для Telegram, написанный на java. " +
                    "Комманда разработки: Агафонов Дмитрий, Сливный Артём";
        }
    */

/*
        MessInterface ref;
        ref = () -> "Привет, я робот! Я умею рассказать о себе по комманде /about, " +
                "а еще могу вернуть погоду, для этого введи /weather" +
                " пожалуйста, указывайте город английскими буквами";
        commandDic.put("/help", ref.getMess());

        ref = () -> "Я - Бот для Telegram, написанный на java. " +
                "Комманда разработки: Агафонов Дмитрий, Сливный Артём";
        commandDic.put("/about", ref.getMess());

        ref = () -> printAboutWeather();
        commandDic.put("/weather", ref.getMess());
*/
//ref = () -> printAboutWeather("Coordinates");
//commandDic.put("/weatherCoord", ref.getMess());

//refArg = (String city) -> printAboutWeather(city);
//commandDic.put("/weather", refArg.getMessWithArg(""));


//var commandDic = new HashMap<String, Operationable>();
        /*
        Operationable operHelp = (String a) -> PrintHelp();
        commandDic.put("/help", operHelp);
        Operationable operAbout = (String a) -> PrintAbout();
        commandDic.put("/about", operAbout);
        Operationable operation = (String a) -> printAboutWeather(a);
        commandDic.put("/weather", operation);
        */

/*
interface MessInterface
{
    String getMess();
}

interface Operationable
{
    String calc(String a);
}
*/