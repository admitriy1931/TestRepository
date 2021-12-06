import advisor.ClothChooser;
import commands.JsonParserResult;
import commands.WeatherIndCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RightWeatherIndAnswerTest {
    @Test
    void getRightWeatherIndAnswer() {
        var jsonWeather = "{\"coord\":{\"lon\":60.52,\"lat\":56.6892},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":4.62,\"feels_like\":1.39,\"temp_min\":4.62,\"temp_max\":4.62,\"pressure\":1017,\"humidity\":87},\"visibility\":10000,\"wind\":{\"speed\":4,\"deg\":240},\"clouds\":{\"all\":75},\"dt\":1635680247,\"sys\":{\"type\":1,\"id\":8985,\"country\":\"RU\",\"sunrise\":1635649485,\"sunset\":1635682691},\"timezone\":18000,\"id\":0,\"name\":\"Горный Щит\",\"cod\":200}";
        var parserResult = new JsonParserResult("4.62", "1017.0", "75.0","Clouds", "04d", "4.0").FormParserOutput();
        assertEquals(parserResult.stringOutput,
                WeatherIndCommand.JSONParser(jsonWeather).FormParserOutput().stringOutput);
        assertEquals(parserResult.recommendation,
                WeatherIndCommand.JSONParser(jsonWeather).FormParserOutput().recommendation);
        var cloth = new ClothChooser(4.62, "04", 4.0, "Clouds");
        assertEquals(cloth.body, "Куртка");
        assertEquals(cloth.legs, "Джинсы");
        assertEquals(cloth.head, "Шапка");
        assertEquals(cloth.feets, "Ботинки");
    }
}
