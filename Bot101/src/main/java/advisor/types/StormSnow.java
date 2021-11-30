package advisor.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class StormSnow implements WeatherType {
    @Override
    public ArrayList<String> getClothesNames(Double temperature, String type, Double wind, String description) {
        if (wind < 10 ||
                !description.contains("snow"))
            return null;
        return new ArrayList<>(Arrays.asList("Шапка", "Парка", "Утепленные джинсы", "Зимние сапоги"));
    }
}
