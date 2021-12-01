package advisor.types;

import advisor.ClothChooser;

public interface WeatherType {
    ClothChooser getClothesNames(Double temperature, String type, Double wind, String description);
}
