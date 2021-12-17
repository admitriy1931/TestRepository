package advisor;

import advisor.types.WeatherType;


public class ClothChooser {
    public String head = "Шапку";
    public String body = "Худи";
    public String legs = "Джинсы";
    public String feets = "Кроссовки";

    public ClothChooser(Double temperature, String type, Double wind, String description) {
        var listOfTypes = new ListOfTypes().List;
        ClothChooser result = null;
        for (WeatherType weatherType : listOfTypes) {
            result = weatherType.getClothesNames(temperature, type, wind, description);
            if (result != null) {
                this.head = result.head;
                this.legs = result.legs;
                this.feets = result.feets;
                this.body = result.body;
                break;
            }
        }
    }

    public ClothChooser(String head, String body, String legs, String feets)
    {
        this.head = head;
        this.body = body;
        this.legs = legs;
        this.feets = feets;
    }
}
