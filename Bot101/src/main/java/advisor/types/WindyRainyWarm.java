package advisor.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class WindyRainyWarm implements WeatherType {

    @Override
    public ArrayList<String> getClothesNames(Double temperature, String type, Double wind, String description) {
        if (wind <= 1 ||
                !description.contains("rain") || temperature <= 10)
            return null;
        return new ArrayList<>(Arrays.asList("Бейсболка", "Ветровка", "Джинсы", "Кеды"));
    }
}
