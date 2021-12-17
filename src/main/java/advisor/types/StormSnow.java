package advisor.types;

import advisor.ClothChooser;


public class StormSnow implements WeatherType {
    @Override
    public ClothChooser getClothesNames(Double temperature, String type, Double wind, String description) {
        if (wind < 10 ||
                !description.contains("snow"))
            return null;
        return new ClothChooser("Шапка", "Парка", "Утепленные джинсы", "Зимние сапоги");
    }
}
