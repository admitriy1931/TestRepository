package advisor;

import commands.JsonParserResult;

public class Advisor {

    public static String formRecommendationFromJson(JsonParserResult jsonParserResult) {

        var temperatureAnalysis = makeTemperatureAnalysis(jsonParserResult.temp);
        var cloudsAnalysis = makeCloudsAnalysis(jsonParserResult.clouds);
        var typeOfWeatherAnalysis = makeIconAnalysis(jsonParserResult.icon);
        return String.format("За окном сегодня %s, на улице %s облачность, снаружи %s", temperatureAnalysis, cloudsAnalysis, typeOfWeatherAnalysis);
    }

    public static String formRecommendationFromText(String inputText) {
        var strings = inputText.split("\n");
        var temp = strings[1];
        var pressure = strings[3];
        var clouds = strings[5];
        var icon = strings[7];
        var jsonResult = new JsonParserResult(temp, pressure, clouds, icon);
        return formRecommendationFromJson(jsonResult);
    }

    private static String makeIconAnalysis(String icon) {
        String result = "";
        switch (icon.substring(0, 2)) {
            case ("11"):
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

    private static String makeCloudsAnalysis(String clouds) {
        var valueClouds = Double.parseDouble(clouds) / 10;
        if (valueClouds <= 1)
            return "нулевая";
        if (valueClouds <= 3)
            return "малая";
        if (valueClouds <= 7)
            return "переменчивая";
        if (valueClouds <= 9)
            return "значительная";
        if (valueClouds <= 10)
            return "сплошная";
        return "неопределенная";
    }

    private static String makeTemperatureAnalysis(String temp) {
        var valueTemp = Double.parseDouble(temp);
        if (valueTemp <= -25)
            return "ледяной ад";
        if (valueTemp <= -10)
            return "сильный холод";
        if (valueTemp <= -5)
            return "холод";
        if (valueTemp <= 5)
            return "прохладно";
        if (valueTemp <= 15)
            return "комфортная температура";
        if (valueTemp <= 25)
            return "тепло";
        return "жара";
    }
}
