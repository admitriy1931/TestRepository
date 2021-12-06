import advisor.ClothChooser;
import commands.JsonParserResult;
import commands.WeatherCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RightWeatherAnswerTest {
    @Test
    void getRightWeatherAnswer() {
        var testsConstants = new Constants();
        var jsonWeather = testsConstants.SimpleWeatherRequestAnswer;
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
