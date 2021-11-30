package advisor.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class WindlessRainyWarm implements WeatherType {
    @Override
    public ArrayList<String> getClothesNames(String temperature, String type, String wind, String description) {
        if (Double.parseDouble(wind) > 1 ||
                !description.contains("rain".toLowerCase(Locale.ROOT)) || Double.parseDouble(temperature) <= 10)
            return null;
        return new ArrayList<>(Arrays.asList("Бейсболка", "Худи", "Джинсы", "Кроссовки"));
    }
}
