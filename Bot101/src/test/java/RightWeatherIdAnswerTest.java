import advisor.ClothChooser;
import commands.JsonParserResult;
import commands.WeatherIdCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RightWeatherIdAnswerTest {
    @Test
    void getRightWeatherIdAnswer() {
        var jsonWeather = "{\"coord\":{\"lon\":60.6125,\"lat\":56.8575},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":277.92,\"feels_like\":274.2,\"temp_min\":277.92,\"temp_max\":277.92,\"pressure\":1017,\"humidity\":81},\"visibility\":10000,\"wind\":{\"speed\":5,\"deg\":250},\"clouds\":{\"all\":75},\"dt\":1635678622,\"sys\":{\"type\":1,\"id\":8985,\"country\":\"RU\",\"sunrise\":1635649497,\"sunset\":1635682634},\"timezone\":18000,\"id\":1486209,\"name\":\"Ekaterinburg\",\"cod\":200}";
        var parserResult = new JsonParserResult("277.92", "1017.0", "75.0","broken clouds", "04d",  "5.0").FormParserOutput();
        assertEquals(parserResult.stringOutput,
                WeatherIdCommand.JSONParser(jsonWeather).FormParserOutput().stringOutput);
        assertEquals(parserResult.recommendation,
                WeatherIdCommand.JSONParser(jsonWeather).FormParserOutput().recommendation);

        var cloth = new ClothChooser(277.92, "04", 5.0, "broken clouds");
        assertEquals(cloth.body, "Лонгслив");
        assertEquals(cloth.legs, "Брюки-карго");
        assertEquals(cloth.head, "Бейсболка");
        assertEquals(cloth.feets, "Кроссовки");
    }
}
