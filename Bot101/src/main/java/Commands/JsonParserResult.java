package commands;
import advisor.WeatherText;

public class JsonParserResult {
    public String temp;
    public String pressure;
    public String clouds;
    public String icon;
    public String wind;

    public JsonParserResult(String temp, String pressure, String clouds, String icon, String wind) {
        this.temp = temp;
        this.pressure = pressure;
        this.clouds = clouds;
        this.icon = icon;
        this.wind = wind;
    }

    public ParserOutput FormParserOutput() {

        var stringOutput =  "temp: " + System.lineSeparator() +
                            this.temp + System.lineSeparator() +
                            "pressure: " + System.lineSeparator() +
                            this.pressure + System.lineSeparator() +
                            "clouds: " + System.lineSeparator() +
                            this.clouds + System.lineSeparator() +
                            "icon: " + System.lineSeparator() +
                            this.icon + System.lineSeparator() +
                            "wind: " + System.lineSeparator() +
                            this.wind + System.lineSeparator();
        var recommendation = WeatherText.FormRecommendation(this);
        var output = new ParserOutput(stringOutput, recommendation);
        return output;
    }
}
