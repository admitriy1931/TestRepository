package advisor;

import advisor.types.WeatherType;
import advisor.types.WindlessRainyCold;
import advisor.types.WindlessRainyWarm;
import advisor.types.WindlessSoftCold;
import advisor.types.WindlessSoftWarm;
import advisor.types.SoftSnow;
import advisor.types.StormSnow;
import advisor.types.WindySoftWarm;
import advisor.types.WindyRainyCold;
import advisor.types.WindySoftCold;
import advisor.types.WindyRainyWarm;

import java.util.ArrayList;

public class ListOfTypes {
    public ArrayList<WeatherType> List = new ArrayList<>();

    public ListOfTypes() {
        List.add(new SoftSnow());
        List.add(new StormSnow());
        List.add(new WindyRainyCold());
        List.add(new WindyRainyWarm());
        List.add(new WindySoftCold());
        List.add(new WindySoftWarm());
        List.add(new WindlessRainyCold());
        List.add(new WindlessRainyWarm());
        List.add(new WindlessSoftCold());
        List.add(new WindlessSoftWarm());
    }
}
