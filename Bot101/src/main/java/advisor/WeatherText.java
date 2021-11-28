package advisor;

import bot.StaticClass;

public class WeatherText {
    public static String FormOfRecommendation() {
        String icon = StaticClass.Icon;
        String parseWeather = StaticClass.tempPressClouds;
        String[] DisparseWeather = parseWeather.split("\n");

        var temperatureAnalysis = MakeTemperatureAnalysis(DisparseWeather[1]);
        var cloudsAnalysis = MakeCloudsAnalysis(DisparseWeather[5]);
        var typeOfWeatherAnalysis = MakeIconAnalysis(icon);

        return String.format("За окном сегодня %s, на улице  облачность %s, снаружи %s", temperatureAnalysis, cloudsAnalysis, typeOfWeatherAnalysis);
        //return "Рекомендуем одеться потеплее";
    }

    private static String MakeIconAnalysis(String icon) {
        String result = "";
        switch(icon.substring(0,2)){
            case("11") :
                result = "гроза";
                break;
            case ("09"):
                result = "моросящий дождь";
                break;
            case ("10"):
                result = "дождь";
                break;
            case ("13"):
                result = "снег";
                break;
            case ("50"):
                result = "туман";
                break;
            case ("01"):
                result = "нет осадков";
                break;
            case ("02"):
                result = "лишь пара облачков на небе";
                break;
            case ("03"):
                result = "облачно";
                break;
            case ("04"):
                result = "всё небо затянуто облаками";
                break;
        }
        return result;
    }

    private static String MakeCloudsAnalysis(String clouds) {
        var valueClouds = Double.parseDouble(clouds)/10;
        if(valueClouds <=1)
            return "нулевая";
        if(valueClouds <=3)
            return "малая";
        if(valueClouds <=7)
            return "переменчивая";
        if(valueClouds <=9)
            return "значительная";
        if(valueClouds <=10)
            return "сплошная";
        return "неопределенная";
    }

    private static String MakeTemperatureAnalysis(String temp) {
        var valueTemp = Double.parseDouble(temp);
        if(valueTemp <=-25)
            return "ледяной ад";
        if(valueTemp <=-10)
            return "сильный холод";
        if(valueTemp <=-5)
            return "холод";
        if(valueTemp <=5)
            return "прохладно";
        if(valueTemp <=15)
            return "комфортная температура";
        if(valueTemp <=25)
            return "тепло";
        return "жара";
    }
}
