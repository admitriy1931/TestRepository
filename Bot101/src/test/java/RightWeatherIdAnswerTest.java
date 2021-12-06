import advisor.ClothChooser;
import commands.JsonParserResult;
import commands.WeatherIdCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RightWeatherIdAnswerTest {
    @Test
    void getRightWeatherIdAnswer() {
        var testsConstants = new Constants();
        var jsonWeather = testsConstants.IdRequestAnswer;
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
