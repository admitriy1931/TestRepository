package advisor.types;

import advisor.ClothChooser;

public class WindlessSoftCold implements WeatherType {
    @Override
    public ClothChooser getClothesNames(Double temperature, String type, Double wind, String description) {
        if (wind > 1 ||
                description.contains("rain") || temperature > 10)
            return null;
        return new ClothChooser("Шапка", "Куртка", "Джинсы", "Походные ботинки");
    }
}
