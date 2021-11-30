package advisor.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class SoftSnow implements WeatherType {
    @Override
    public ArrayList<String> getClothesNames(Double temperature, String type, Double wind, String description) {
        if (wind > 10 ||
                !description.contains("snow"))
            //.toLowerCase(Locale.ROOT) ?????
            return null;
        return new ArrayList<>(Arrays.asList("Шапка", "Пуховик", "Утепленные джинсы", "Теплые ботинки"));
    }
}
