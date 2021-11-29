package advisor.types;

import java.util.ArrayList;

public interface Type {
    ArrayList<String> getClothName(String temperature, String type, String wind, String description);
}
