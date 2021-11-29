package advisor;

import java.util.ArrayList;
import java.util.List;

public class ClothChooser {
    public String Head = "Шапку";
    public String Body = "Худи";
    public String Legs = "Джинсы";
    public String Feets = "Кроссовки";

    public ClothChooser(String temperature, String type, String wind)
    {
        var listOfTypes = ListOfTypes.TYPES;
        ArrayList<String> result = null;
        for (Type weatherType: listOfTypes)
        {
            result = weatherType.getClothName(temperature, type, wind);
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
