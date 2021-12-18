package commands;

import advisor.WeatherAnalyzer;

public class JsonParserResult {
    public String temp;
    public String pressure;
    public String clouds;
    public String icon;
    public String wind;
    public String main;

    public JsonParserResult(String temp, String pressure, String clouds, String main, String icon, String wind) {
        this.temp = temp;
        this.pressure = pressure;
        this.clouds = clouds;
        this.icon = icon;
        this.wind = wind;
        this.main = main;
    }

    public ParserOutput FormParserOutput() {

        var stringOutput = "temp: " + System.lineSeparator() +
                this.temp + System.lineSeparator() +
                "pressure: " + System.lineSeparator() +
                this.pressure + System.lineSeparator() +
                "clouds: " + System.lineSeparator() +
                this.clouds + System.lineSeparator() +
                "icon: " + System.lineSeparator() +
                this.icon + System.lineSeparator() +
                "wind: " + System.lineSeparator() +
                this.wind + System.lineSeparator() +
                "description: " + System.lineSeparator() +
                this.main + System.lineSeparator();
        var recommendation = WeatherAnalyzer.formRecommendationForJSON(this);
        return new ParserOutput(stringOutput, recommendation, this);
    }
}
