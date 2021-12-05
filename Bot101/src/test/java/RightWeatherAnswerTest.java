import advisor.ClothChooser;
import commands.JsonParserResult;
import commands.WeatherCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RightWeatherAnswerTest {
    @Test
    void getRightWeatherAnswer() {
        var jsonWeather = "{\"coord\":{\"lon\":60.6125,\"lat\":56.8575},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"base\":\"stations\",\"main\":{\"temp\":1.77,\"feels_like\":-3.2,\"temp_min\":1.77,\"temp_max\":1.77,\"pressure\":1013,\"humidity\":100},\"visibility\":10000,\"wind\":{\"speed\":6,\"deg\":300},\"rain\":{\"1h\":0.11},\"clouds\":{\"all\":90},\"dt\":1635620614,\"sys\":{\"type\":1,\"id\":8985,\"country\":\"RU\",\"sunrise\":1635649497,\"sunset\":1635682634},\"timezone\":18000,\"id\":1486209,\"name\":\"Ekaterinburg\",\"cod\":200}";
        var parserResult = new JsonParserResult("1.77", "1013.0", "90.0", "light rain", "10n", "6.0").FormParserOutput();
        assertEquals(parserResult.stringOutput,
                WeatherCommand.JSONParser(jsonWeather).FormParserOutput().stringOutput);
        assertEquals(parserResult.recommendation,
                WeatherCommand.JSONParser(jsonWeather).FormParserOutput().recommendation);
        var cloth = new ClothChooser(1.77, "10", 6.0, "light rain");
        assertEquals(cloth.body, "Куртка");
        assertEquals(cloth.legs, "Джинсы");
        assertEquals(cloth.head, "Шапка");
        assertEquals(cloth.feets, "Ботинки");

    }
}
