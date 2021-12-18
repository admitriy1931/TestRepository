import commands.JsonParserResult;
import commands.WeatherCordCommand;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RightWeatherCordAnswerTest {
    @Test
    void getRightWeatherCordAnswer() {
        var testConstants = new Constants();
        var jsonWeather = testConstants.CORDINATE_REQUEST_ANSWER;

        var result
                = Arrays.asList("temp: ", -9.19, "pressure: ", 1017.0, "clouds: ", 87.0, "icon", "04n");
        var parserResult = new JsonParserResult("-9.19", "1017.0",
                "87.0", "overcast clouds", "04n", "2.53").FormParserOutput();
        assertEquals(parserResult.stringOutput,
                WeatherCordCommand.JSONParser(jsonWeather).FormParserOutput().stringOutput);
    }
}
