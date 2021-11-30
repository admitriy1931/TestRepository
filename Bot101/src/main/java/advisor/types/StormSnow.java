package advisor.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class StormSnow implements WeatherType {
    @Override
    public ArrayList<String> getClothesNames(String temperature, String type, String wind, String description) {
        if (Double.parseDouble(wind) < 10 ||
                !description.contains("snow".toLowerCase(Locale.ROOT)))
            return null;
        return new ArrayList<>(Arrays.asList("Шапка", "Парка", "Утепленные джинсы", "Зимние сапоги"));
    }
}
