package advisor.types;

import java.util.ArrayList;

public interface WeatherType {
    ArrayList<String> getClothesNames(Double temperature, String type, Double wind, String description);
}
