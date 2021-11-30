package advisor;

import advisor.types.WeatherType;

import java.util.ArrayList;

public class ClothChooser {
    public String head = "Шапку";
    public String body = "Худи";
    public String legs = "Джинсы";
    public String feets = "Кроссовки";

    public ClothChooser(Double temperature, String type, Double wind, String description) {
        var listOfTypes = new ListOfTypes().List;
        var result = new ArrayList<String>();
        for (WeatherType weatherType : listOfTypes) {
            result = weatherType.getClothesNames(temperature, type, wind, description);
            if (result != null) {
                head = result.get(0);
                body = result.get(1);
                legs = result.get(2);
                feets = result.get(3);
                break;
            }
        }
    }
}
