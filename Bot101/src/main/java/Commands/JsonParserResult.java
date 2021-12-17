package commands;

import advisor.Advisor;

public class JsonParserResult {
    public String temp;
    public String pressure;
    public String clouds;
    public String icon;

    public JsonParserResult(String temp, String pressure, String clouds, String icon) {
        this.temp = temp;
        this.pressure = pressure;
        this.clouds = clouds;
        this.icon = icon;
    }

    public ParserOutput FormParserOutput() {

        var stringOutput = "temp: " + System.lineSeparator() +
                this.temp + System.lineSeparator() +
                "pressure: " + System.lineSeparator() +
                this.pressure + System.lineSeparator() +
                "clouds: " + System.lineSeparator() +
                this.clouds + System.lineSeparator() +
                "icon: " + System.lineSeparator() +
                this.icon + System.lineSeparator();
        var recommendation = Advisor.formRecommendationFromJson(this);

        return new ParserOutput(stringOutput, recommendation);
    }
}
