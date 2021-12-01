package advisor.types;

import advisor.ClothChooser;


public class SoftSnow implements WeatherType {
    @Override
    public ClothChooser getClothesNames(Double temperature, String type, Double wind, String description) {
        if (wind > 10 ||
                !description.contains("snow"))
            //.toLowerCase(Locale.ROOT) ?????
            return null;
        return new ClothChooser("Шапка", "Пуховик", "Утепленные джинсы", "Теплые ботинки");
    }
}
