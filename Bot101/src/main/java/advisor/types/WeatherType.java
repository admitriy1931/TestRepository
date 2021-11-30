package advisor.types;

import java.util.ArrayList;

public interface WeatherType {
    ArrayList<String> getClothesNames(String temperature, String type, String wind, String description);
}
