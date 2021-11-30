package advisor;

import advisor.types.WeatherType;

import java.util.ArrayList;

public class ClothChooser {
    public String Head = "Шапку";
    public String Body = "Худи";
    public String Legs = "Джинсы";
    public String Feets = "Кроссовки";

    public ClothChooser(String temperature, String type, String wind, String description) {
        var listOfTypes = new ListOfTypes().List;
        ArrayList<String> result = null;
        for (WeatherType weatherType : listOfTypes) {
            result = weatherType.getClothesNames(temperature, type, wind, description);
            if (result != null) {
                Head = result.get(0);
                Body = result.get(1);
                Legs = result.get(2);
                Feets = result.get(3);
                break;
            }
        }
    }
}
